package com.monits.scraper.service;

import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

import com.monits.scraper.requests.RequestGenerator;
import com.monits.scraper.transformation.Transformation;

public class ScrapingServiceImpl implements ScrapingService {

	@Override
	public String scrap (RequestGenerator rGen,	Transformation transform)
		   throws ScrapingServiceException {

		HttpResponse response;
		String data = null;

		try {
			response = performRequest(rGen);
			data = EntityUtils.toString(response.getEntity());
			data = transform.transform(data);

		} catch (Exception e) {
			throw new ScrapingServiceException(e.getMessage());
		}

		/* TODO : Sanitize HTML */

		return data;
	}

	/**
	 * Using a RequestGenerator, generate the http request, performs it
	 * and returns it's response.
	 * This throws a Exception when client.execute returns a Exception
	 * @param Requestgenerator
	 * @return HttpResponse
	 * @throws Exception
	 */
	private HttpResponse performRequest(RequestGenerator requestParams)
			throws Exception {

		DefaultHttpClient client = new DefaultHttpClient();
		HttpUriRequest request = null;

		switch (requestParams.getVerb()) {
		case GET:
			request = new HttpGet(requestParams.getUrl());
			break;

		case POST:
			request = new HttpPost(requestParams.getUrl());
			break;
		case DELETE:
			request = new HttpDelete(requestParams.getUrl());
			break;
		case PUT:
			request = new HttpPut(requestParams.getUrl());
			break;

		}

		CookieStore cookieStore = new BasicCookieStore();

		client.setCookieStore(cookieStore);

		if (requestParams.getCookie() != null) {

			Map<String,String> cookieMap = requestParams.getCookie();

			Iterator<Map.Entry<String, String>> cookieIterator = cookieMap.entrySet().iterator();

			while (cookieIterator.hasNext()) {
				Map.Entry<String, String> e = cookieIterator.next();

				cookieStore.addCookie(new BasicClientCookie(e.getKey(), e.getValue()));

			}
		}

		if (requestParams.getUserAgent() != null) {

			request.setHeader("User-Agent",requestParams.getUserAgent());
		}

		return client.execute(request);

	}

}