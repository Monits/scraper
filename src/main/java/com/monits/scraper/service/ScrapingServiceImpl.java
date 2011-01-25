package com.monits.scraper.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.monits.scraper.RequestGenerator;
import com.monits.scraper.transformation.Transformation;

public class ScrapingServiceImpl implements ScrapingService {

	@Override
	public String scrap (RequestGenerator rGen, Transformation transform) {

		HttpResponse response = performRequest(rGen);
		String data = null;

		try {
			data = EntityUtils.toString(response.getEntity());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO : Sanitize HTML

		return transform.transform(data);
	}

	private HttpResponse performRequest(RequestGenerator req){

		return null;
	}


}
