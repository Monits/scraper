/**
 * Constant Request Generator Test
 *
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.scraper.requests;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Constant Request Generator Test
 *
 * @author Gaston Muñiz <gmuniz@monits.com>
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class ConstantRequestGeneratorTest {

	Map<String, String> cookies;

	/**
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cookies = new HashMap<String, String>();
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

		String url = "http://www.google.com";
		RequestVerb verb = RequestVerb.GET;
		String userAgent = "Mozilla Firefox 3.2.1.2.3";
		cookies.put("guid", "1F074B19C51E42C5A5175C6F7D");

		// Constructors Tests
		ConstantRequestGenerator request1 = new ConstantRequestGenerator(url);

		Assert.assertEquals("Constructor failed", url, request1.getUrl());

		ConstantRequestGenerator request2 =
										new ConstantRequestGenerator(url, verb);

		Assert.assertEquals("Constructor failed", url, request2.getUrl());
		Assert.assertEquals("Constructor failed", verb, request2.getVerb());

		ConstantRequestGenerator request3 =
					new ConstantRequestGenerator(url, verb, userAgent, cookies);

		Assert.assertEquals("Constructor failed", url, request3.getUrl());
		Assert.assertEquals("Constructor failed", verb, request3.getVerb());
		Assert.assertEquals("Constructor failed", userAgent, request3.getUserAgent());
		Assert.assertEquals("Constructor failed", cookies, request3.getCookies());

		//Setters test
		request1.setUrl(url);
		Assert.assertEquals("failed setting url", url, request1.getUrl());

		request1.setUserAgent(userAgent);
		Assert.assertEquals("failed setting userAgent", userAgent, request1.getUserAgent());

		request1.setCookies(cookies);
		Assert.assertEquals("failed setting url", cookies, request1.getCookies());

		request1.setVerb(verb);
		Assert.assertEquals("failed setting url", verb, request1.getVerb());

	}

}
