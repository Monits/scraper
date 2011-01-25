/**
 * Test for the class ConstantRequestGenerator
 * 
 * @author    ndebernardi <ndebernardi@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
package com.monits.scraper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for the class ConstantRequestGenerator
 * 
 * @author    ndebernardi <ndebernardi@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */

public class ConstantRequestGeneratorTest {
	
	ConstantRequestGenerator crg = new ConstantRequestGenerator();
	
	private String url, verb, session, cookie, userAgent;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for ConstantRequestGenerator.ConstantRequestGenerator()}.
	 */
	@Test
	public void testConstantRequestGenerator() {
		
		/**
		 * Testing setters, this should work 
		 */
		this.crg.setUrl("http://www.google.com");
		this.crg.setCookie("username=J23Esta expires=Tuesday,November18,2011,10:23:05am path=/tutorials/ domain=LocalHost ");
		//this.crg.setSession("userId= 990Auj");
		this.crg.setUserAgent("Mozilla Firefox 3.2.1.2.3");
		this.crg.setVerb("GET");
		
		/**
		 * Testing getters, this should work
		 */
		this.url = crg.getUrl();
		this.cookie = crg.getCookie();
		//this.session= crg.getSession();
		this.userAgent= crg.getUserAgent();
		this.verb = crg.getVerb();
		
		/**
		 * Asserts for every case.
		 */
		Assert.assertEquals("URL get and set doesn't work", "http://www.google.com", url);
		Assert.assertEquals("Cookie get and set doesn't work", "username=J23Esta expires=Tuesday,November18,2011,10:23:05am path=/tutorials/ domain=LocalHost ", cookie);
		//Assert.assertEquals("Session get and set doesn't work", "userId= 990Auj", session);
		Assert.assertEquals("userAgent get and set doesn't work", "Mozilla Firefox 3.2.1.2.3", userAgent);
		Assert.assertEquals("Verb get and set doesn't work", "GET", verb);
	}

}
