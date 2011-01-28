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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.monits.scraper.requests.RequestGenerator;
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
			
		default:
			throw new Exception("No Support for "
					+ requestParams.getVerb() + " HTTP verb");
		}

		if (request instanceof HttpEntityEnclosingRequestBase) {
			
			if (requestParams.getBody() != null) {
				request.setHeader(
						"Content-Type","application/x-www-form-urlencoded");
			
				UrlEncodedFormEntity bodyEntity = buildBody(requestParams);
				((HttpEntityEnclosingRequestBase)request).setEntity(bodyEntity);
			}
		}
		
		if (requestParams.getCookies() != null) {
			
			CookieStore cookieStore = buildCookies(requestParams);
			client.setCookieStore(cookieStore);
		}
		
		if (requestParams.getUserAgent() != null) {
			request.setHeader("User-Agent", requestParams.getUserAgent());
		}
		
		return client.execute(request);

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
}
