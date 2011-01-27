/**
 * Test for the class ScrapingServiceImpl
 * 
 * @author    jmrolon <jmrolon@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
package com.monits.scraper.service;


import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.monits.scraper.requests.RequestGenerator;
import com.monits.scraper.requests.RequestVerb;
import com.monits.scraper.transformation.Identity;
import com.monits.scraper.transformation.Transformation;

/**
* Test for the class ScrapingServiceImpl
* 
* @author    jmrolon <jmrolon@monits.com>
* @copyright 2011 Monits
* @license   Copyright (C) 2011. All rights reserved
* @version   Release: 1.0.0
* @link      http://www.monits.com/
* @since     1.0.0
* @see 	     {@link ScrapingServiceImpl}
*/
public class ScrapingServiceImplTest {

	
	private Transformation trans = new Identity();
	private ScrapingServiceImpl sService;
	
	@Before
	public void setUp() throws Exception {
		sService = new ScrapingServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test for the method ScrapingService.scrap(RequestGenerator rGen, 
	 * Transformation transform)
	 * Check the return with two different types of UserAgent from a page 
	 * who support both types
	 * The returns obviously have to be different
	 * 
	 * @throws ScrapingServiceException
	 */
	@Test
	public void testScrapMobilePcBrowser() throws ScrapingServiceException {

		
		RequestGenerator requestFF = EasyMock
			.createMock(RequestGenerator.class);
		RequestGenerator requestMobile = EasyMock
			.createMock(RequestGenerator.class);
		
		EasyMock.expect(requestFF.getUrl())
			.andReturn("http://www.minijuegos.com").anyTimes();
		EasyMock.expect(requestFF.getUserAgent())
			.andReturn("Mozilla Firefox 3.2.1.2.3").anyTimes();
		EasyMock.expect(requestFF.getCookies())
			.andReturn(null).anyTimes();
		EasyMock.expect(requestFF.getVerb()).andReturn(RequestVerb.GET).anyTimes();
		
		EasyMock.expect(requestMobile.getUrl())
			.andReturn("http://www.minijuegos.com").anyTimes();
		EasyMock.expect(requestMobile.getUserAgent())
			.andReturn("Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us").times(4);
		EasyMock.expect(requestMobile.getCookies())
			.andReturn(null).anyTimes();
		EasyMock.expect(requestMobile.getVerb())
			.andReturn(RequestVerb.GET).anyTimes();
		
		EasyMock.replay(requestFF, requestMobile);
		
		Assert.assertNotNull("Firefox Request Failed", sService.scrap(requestFF, trans));
		String pruebaPcBrowser = sService.scrap(requestFF, trans);
		
		Assert.assertNotNull("Mobile Request Failed", sService
					.scrap(requestMobile, trans));
		String pruebaMobileBrowser = sService.scrap(requestMobile, trans);
		
		Assert.assertNotSame(pruebaMobileBrowser, pruebaPcBrowser);
	}
	
	/**
	 * Test for the method ScrapingService.scrap(RequestGenerator rGen,
	 * Transformation transform)
	 * Check the return with and without "guid" cookie from a page who will 
	 * response a different "html" in each case
	 * They have to be different
	 * 
	 * @throws ScrapingServiceException
	 */
	@Test
	public void testScrapCookieWeb() throws ScrapingServiceException {
		
		RequestGenerator requestCookie = EasyMock
			.createMock(RequestGenerator.class);
		RequestGenerator requestWithoutCookie = EasyMock
			.createMock(RequestGenerator.class);
		
		String pruebaConCookie;
		String pruebaSinCookie;
		
		Map<String, String> cookie = new HashMap<String, String>();
		cookie.put("guid", "1F074B19C51E42C5A5175C6F7D");
		
		EasyMock.expect(requestCookie.getUrl())
			.andReturn("http://www.cotodigital.com.ar/novedades.asp")
			.anyTimes();
		EasyMock.expect(requestCookie.getUserAgent())
			.andReturn("Mozilla Firefox 3.2.1.2.3").anyTimes();
		EasyMock.expect(requestCookie.getCookies())
			.andReturn(cookie).anyTimes();
		EasyMock.expect(requestCookie.getVerb())
			.andReturn(RequestVerb.GET).anyTimes();
		
		EasyMock.replay(requestCookie);
		
		EasyMock.expect(requestWithoutCookie.getUrl())
			.andReturn("http://www.cotodigital.com.ar/novedades.asp")
				.anyTimes();
		EasyMock.expect(requestWithoutCookie.getUserAgent())
			.andReturn("Mozilla Firefox 3.2.1.2.3").anyTimes();
		EasyMock.expect(requestWithoutCookie.getCookies())
			.andReturn(null).anyTimes();
		EasyMock.expect(requestWithoutCookie.getVerb())
			.andReturn(RequestVerb.GET).anyTimes();
		
		EasyMock.replay(requestWithoutCookie);
		
		pruebaConCookie = sService.scrap(requestCookie, trans);
		Assert.assertNotNull("Request With Cookie Failed",sService
				.scrap(requestCookie, trans));
		
		pruebaSinCookie = sService.scrap(requestCookie, trans);
		Assert.assertNotNull("Request Without Cookie Failed",sService
				.scrap(requestWithoutCookie, trans));
		
		Assert.assertNotSame(pruebaConCookie, pruebaSinCookie);
	
	}
	
}

