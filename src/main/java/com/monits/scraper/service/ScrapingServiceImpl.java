/**
 * Class that implements the ScrapingService interface.
 *
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.scraper.service;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.monits.scraper.requests.RequestGenerator;
import com.monits.scraper.sanitation.Sanitation;
import com.monits.scraper.sanitation.SanitationHtmlCleaner;
import com.monits.scraper.transformation.Transformation;

/**
 * Class that implements the ScrapingService interface.
 *
 * @author Gaston Muñiz <gmuniz@monits.com>
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class ScrapingServiceImpl implements ScrapingService {

	private static final int DEFAULT_TIMEOUT = 60000;

	protected Sanitation htmlSanitizer = new SanitationHtmlCleaner();
	private ClientConnectionManager connManager;
	private int timeout = DEFAULT_TIMEOUT;

	/**
	 * Default constructor.
	 */
	public ScrapingServiceImpl() {
		// Create a connection manager that will trust all certificates
		TrustStrategy ts = new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
				// Trust everything
				return true;
			}
		};

		try {
			SSLSocketFactory ssf = new SSLSocketFactory(ts);
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("https", 443, ssf));
			schemeRegistry.register(new Scheme("http", 80, new PlainSocketFactory()));
			connManager = new ThreadSafeClientConnManager(schemeRegistry);

		} catch (KeyManagementException e) { // NOPMD - ignore this exception
			// ignore
		} catch (UnrecoverableKeyException e) { // NOPMD - ignore this exception
			// ignore
		} catch (NoSuchAlgorithmException e) { // NOPMD - ignore this exception
			// ignore
		} catch (KeyStoreException e) { // NOPMD - ignore this exception
			// ignore
		}
	}

	@Override
	public String scrap(RequestGenerator rGen,	Transformation transform)
		throws ScrapingServiceException {

		HttpResponse response;
		String data = null;

		try {
			response = performRequest(rGen);
			data = EntityUtils.toString(response.getEntity());
			data = htmlSanitizer.sanitize(data);
			data = transform.transform(data);

		} catch (Exception e) {
			throw new ScrapingServiceException("Scraping Service Failure", e);
		}

		return data;
	}

	/**
	 * Using a RequestGenerator, generate the http request, performs it
	 * and returns it's response.
	 * This method throws a Exception when client.execute or UrlEncodedFormEntity
	 * constructor throws any Exception
	 *
	 * @param Requestgenerator
	 * @return HttpResponse
	 * @throws Exception
	 */
	private HttpResponse performRequest(RequestGenerator requestParams)
		throws Exception {

		DefaultHttpClient client = new DefaultHttpClient(connManager);
		HttpUriRequest request = getRequestObject(requestParams);

		// Set the timeout
		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeout);
		HttpConnectionParams.setSoTimeout(params, timeout);

		if (request instanceof HttpEntityEnclosingRequestBase
				&& requestParams.getBody() != null) {

			requestParams.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");

			UrlEncodedFormEntity bodyEntity = buildBody(requestParams);
			((HttpEntityEnclosingRequestBase)request).setEntity(bodyEntity);
		}

		if (requestParams.getCookies() != null) {

			CookieStore cookieStore = buildCookies(requestParams);
			client.setCookieStore(cookieStore);
		}

		Map<String, String> headers = requestParams.getHeaders();
		if (!headers.isEmpty()) {

			for (Entry<String, String> entry : headers.entrySet()) {
				request.setHeader(entry.getKey(), entry.getValue());
			}
		}

		return client.execute(request);

	}

	/**
	 * Instances new request for the given params. This instance is being mapped to the given verb, and url.
	 *
	 * @param requestParams
	 *
	 * @return A HttpUriRequest mapped to the given verb.
	 *
	 * @throws Exception
	 */
	private HttpUriRequest getRequestObject(RequestGenerator requestParams) throws Exception {
		switch (requestParams.getVerb()) {
			case GET:
				return new HttpGet(requestParams.getUrl());

			case POST:
				return new HttpPost(requestParams.getUrl());

			case DELETE:
				return new HttpDelete(requestParams.getUrl());

			case PUT:
				return new HttpPut(requestParams.getUrl());

			default:
				throw new Exception("No Support for " + requestParams.getVerb() + " HTTP verb");
		}
	}

	/**
	 * Transforms all the items of a Map<String,String> into a
	 * UrlEncodedFormEntity, this is used as body for a PUT/POST request.
	 *
	 * @param requestParams
	 * @return the body for the a POST/PUT request
	 * @throws Exception
	 */
	private UrlEncodedFormEntity buildBody(RequestGenerator requestParams)
		throws Exception {

		Iterator<Map.Entry<String, String>> bodyIterator = null;
		List<NameValuePair> bodyValues = new ArrayList<NameValuePair>();

		Map<String,String> bodyMap = requestParams.getBody();
		bodyIterator = bodyMap.entrySet().iterator();

		while (bodyIterator.hasNext()) {
			Map.Entry<String, String> bodyValuePair = bodyIterator.next();

			bodyValues.add(new BasicNameValuePair(
					bodyValuePair.getKey(), bodyValuePair.getValue()));
		}

		return new UrlEncodedFormEntity(bodyValues,HTTP.UTF_8);

	}

	/**
	 * Transform all the items in a Map<String,String>
	 * to cookies into a CookieStore.
	 *
	 * @param requestParams
	 * @return a CookieStore with all the cookies needed for the request
	 * @throws Exception
	 */
	private CookieStore buildCookies(RequestGenerator requestParams)
		throws Exception {

		Iterator<Map.Entry<String, String>> cookiesIterator = null;
		CookieStore cookieStore = new BasicCookieStore();
		Map<String,String> cookieMap = requestParams.getCookies();

		cookiesIterator = cookieMap.entrySet().iterator();

		while (cookiesIterator.hasNext()) {
			Map.Entry<String, String> cookieValuePair = cookiesIterator.next();

			cookieStore.addCookie(new BasicClientCookie(
					cookieValuePair.getKey(), cookieValuePair.getValue()));
		}

		return cookieStore;
	}

	@Override
	public void setSanitizer(Sanitation sanitizer) {
		this.htmlSanitizer = sanitizer;
	}

	@Override
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
