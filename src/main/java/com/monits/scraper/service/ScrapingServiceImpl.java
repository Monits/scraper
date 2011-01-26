package com.monits.scraper.service;


import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;

import com.monits.scraper.RequestGenerator;
import com.monits.scraper.transformation.Transformation;

public class ScrapingServiceImpl implements ScrapingService {

	@Override
	public String scrap (RequestGenerator rGen,
							Transformation transform) throws Exception {

		HttpResponse response;
		String data = null;

		try {
			response = performRequest(rGen);
			data = EntityUtils.toString(response.getEntity());
			data = transform.transform(data);

		} catch (Exception e) {
			e.printStackTrace();
				throw new Exception("ScrapingService Exception");
		}

		/* TODO : Sanitize HTML */

		return data;
	}

	/**
	 * Using a RequestGenerator, generate the request, performs it and returns it's response
	 * @param Requestgenerator
	 * @return HttpResponse
	 */
	private HttpResponse performRequest(RequestGenerator requestParams) {


		DefaultHttpClient client = new DefaultHttpClient();
		HttpUriRequest request = null;

		switch (requestParams.getVerb()){
		case GET:
			request = new HttpGet(requestParams.getUrl());
			break;

		}

		CookieStore cookieStore = new BasicCookieStore();

		client.setCookieStore(cookieStore);

		if (requestParams.getCookie() != null) {

			Map<String,String> cookieMap = null;

			Iterator cookieIterator = cookieMap.entrySet().iterator();

			while (cookieIterator.hasNext()) {
				Map.Entry e = (Map.Entry)cookieIterator.next();

				cookieStore.addCookie(new BasicClientCookie(e.getKey().toString(), e.getValue().toString()));

			}
		}

		if (requestParams.getUserAgent() != null) {

			request.setHeader("User-Agent",requestParams.getUserAgent());
		}

		HttpResponse requestResponse = null;

		try {
			requestResponse = client.execute(request);
		} catch (Exception e) {

		}

		return requestResponse;

	}

}
