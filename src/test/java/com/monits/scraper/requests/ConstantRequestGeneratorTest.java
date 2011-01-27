/**
 * Test for the class ConstantRequestGenerator
 * 
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
package com.monits.scraper.requests;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.monits.scraper.requests.ConstantRequestGenerator;
import com.monits.scraper.requests.RequestVerb;

/**
 * Test for the class ConstantRequestGenerator
 * 
 * @author    Nicolas De Bernardi <ndebernardi@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public class ConstantRequestGeneratorTest {
	
	ConstantRequestGenerator crg = new ConstantRequestGenerator();
	
	private String url, userAgent;
	private Map<String, String> cookies = new HashMap<String, String>();
	private RequestVerb verb; 

	/**
	 * Setup cookies
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		cookies.put("Cookie1", "HolaquetalSoyLaCookie1");
		cookies.put("Cookie2", "imTheCookie2");
		cookies.put("Cookie3", "ImTheCookie3");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for ConstantRequestGenerator.ConstantRequestGenerator().
	 */
	@Test
	public void testConstantRequestGenerator() {
		// Testing setters, this should work 
		this.crg.setUrl("http://www.google.com");
		this.crg.setCookie(cookies);
		this.crg.setUserAgent("Mozilla Firefox 3.2.1.2.3");
		this.crg.setVerb(verb);
		
		// Testing getters, this should work
		this.url = crg.getUrl();
		this.cookies = crg.getCookie();
		this.userAgent= crg.getUserAgent();
		this.verb = crg.getVerb();
		
		// Asserts for every case.
		Assert.assertEquals("URL get and set doesn't work", url, crg.getUrl());
		Assert.assertEquals("Cookie get and set doesn't work",cookies, crg.getCookie());
		Assert.assertEquals("userAgent get and set doesn't work",userAgent, crg.getUserAgent());
		Assert.assertEquals("Verb get and set doesn't work", verb, crg.getVerb());
	}

}
