package com.monits.scraper.service;


import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.monits.scraper.requests.ConstantRequestGenerator;
import com.monits.scraper.requests.RequestVerb;
import com.monits.scraper.transformation.Identity;
import com.monits.scraper.transformation.Transformation;

public class ScrapingServiceImplTest {

	ConstantRequestGenerator requestParams = new ConstantRequestGenerator();
	Transformation trans = new Identity();
	ScrapingServiceImpl sService = new ScrapingServiceImpl();
	
	private Map<String, String> cookies = new HashMap<String, String>();
    
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void scrapTest() throws ScrapingServiceException {
		
		cookies.put("Cookie1", "HolaquetalSoyLaCookie1");
		cookies.put("Cookie2", "imTheCookie2");
		cookies.put("Cookie3", "ImTheCookie3");
		
		requestParams.setUrl("http://www.google.com");
		requestParams.setCookie(cookies);
		requestParams.setUserAgent("Mozilla Firefox 3.2.1.2.3");
		requestParams.setVerb(RequestVerb.GET);
	
		System.out.print(sService.scrap(requestParams, trans));
		Assert.assertNotNull("Request Succesfull", sService.scrap(requestParams, trans));
			
	}
		
}
