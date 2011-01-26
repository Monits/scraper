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
			data = transform.transform(data);
		} catch (ParseException e) {
			// TODO Hande ParseException
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Handle IOException
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Exception
		}

		// TODO : Sanitize HTML

		return data;
	}

	private HttpResponse performRequest(RequestGenerator req){

		return null;
	}


}
