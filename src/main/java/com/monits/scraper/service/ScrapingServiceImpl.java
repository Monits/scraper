package com.monits.scraper.service;

import org.apache.commons.httpclient.params.HttpClientParams;

import com.monits.scraper.RequestGenerator;
import com.monits.scraper.transformation.Transformation;

public class ScrapingServiceImpl implements ScrapingService {

	@Override
	public String scrap(RequestGenerator req, Transformation transform) {
		this.generateRequest(req);

		return null;
	}

	private HttpClientParams generateRequest(RequestGenerator req){

		return null;
	}


}
