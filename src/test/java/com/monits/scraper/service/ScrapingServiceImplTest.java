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

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.monits.scraper.requests.RequestGenerator;
import com.monits.scraper.requests.RequestVerb;
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
	 * Transformation transform),
	 * Check the returns with two different types of UserAgent from a page 
	 * who support both types ,the returns obviously have to be different
	 * 
	 * @throws ScrapingServiceException
	 */
	@Test
	public void testScrapMobilePcBrowser() throws ScrapingServiceException {

		RequestGenerator requestFF = EasyMock
			.createMock(RequestGenerator.class);
		RequestGenerator requestMobile = EasyMock
			.createMock(RequestGenerator.class);
		Transformation trans = EasyMock.createMock(Transformation.class);
		
		String responseFFUserAgent;
		String responseMobileUserAgent;
	
		final Capture<String> xhtmlCapture = new Capture<String>();
		
		EasyMock.expect(requestFF.getUrl())
			.andReturn("http://www.gmail.com").anyTimes();
		EasyMock.expect(requestFF.getUserAgent())
			.andReturn("Mozilla Firefox 3.2.1.2.3").anyTimes();
		EasyMock.expect(requestFF.getCookies())
			.andReturn(null).anyTimes();
		EasyMock.expect(requestFF.getVerb()).andReturn(RequestVerb.GET)
			.anyTimes();
		
		EasyMock.expect(requestMobile.getUrl())
			.andReturn("http://www.gmail.com").anyTimes();
		EasyMock.expect(requestMobile.getUserAgent())
			.andReturn("Mozilla/5.0 (iPhone; U; CPU iPhone " +
			"OS 4_0 like Mac OS X; en-us").anyTimes();
		EasyMock.expect(requestMobile.getCookies())
			.andReturn(null).anyTimes();
		EasyMock.expect(requestMobile.getVerb())
			.andReturn(RequestVerb.GET).anyTimes();
		
		EasyMock.expect(trans.transform(EasyMock.capture(xhtmlCapture)))
		.andAnswer(new IAnswer<String>() {

			@Override
			public String answer() throws Throwable {
				return xhtmlCapture.getValue();
			}
			
		}).anyTimes();
		
		EasyMock.replay(requestFF, requestMobile, trans);
		
		Assert.assertNotNull("Firefox Request Failed", sService
				.scrap(requestFF, trans));
		
		responseFFUserAgent = sService.scrap(requestFF, trans);
		
		Assert.assertNotNull("Mobile Request Failed", sService
					.scrap(requestMobile, trans));
		responseMobileUserAgent = sService.scrap(requestMobile, trans);
		
		Assert.assertNotSame("Both response equals, Failed" ,responseFFUserAgent, responseMobileUserAgent);
		
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
		Transformation trans = EasyMock.createMock(Transformation.class);
		
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
		
		EasyMock.expect(requestWithoutCookie.getUrl())
			.andReturn("http://www.cotodigital.com.ar/novedades.asp")
				.anyTimes();
		EasyMock.expect(requestWithoutCookie.getUserAgent())
			.andReturn("Mozilla Firefox 3.2.1.2.3").anyTimes();
		EasyMock.expect(requestWithoutCookie.getCookies())
			.andReturn(null).anyTimes();
		EasyMock.expect(requestWithoutCookie.getVerb())
			.andReturn(RequestVerb.GET).anyTimes();
		
		final Capture<String> xhtmlCapture = new Capture<String>();
		
		EasyMock.expect(trans.transform(EasyMock.capture(xhtmlCapture)))
			.andAnswer(new IAnswer<String>() {

				@Override
				public String answer() throws Throwable {
					return xhtmlCapture.getValue();
				}
				
			}).anyTimes();
		
		EasyMock.replay(requestWithoutCookie, requestCookie, trans);
		
		pruebaConCookie = sService.scrap(requestCookie, trans);
		Assert.assertNotNull("Request With Cookie Failed",sService
				.scrap(requestCookie, trans));
		
		pruebaSinCookie = sService.scrap(requestCookie, trans);
		Assert.assertNotNull("Request Without Cookie Failed",sService
				.scrap(requestWithoutCookie, trans));
		
		Assert.assertNotSame("Both response equals, Failed", pruebaConCookie, pruebaSinCookie);
		
	}
	
	/**
	 * Test for the method ScrapingService.scrap(RequestGenerator rGen,
	 * Transformation transform)
	 * Check the exception thrown, it has to be one of our custom 
	 * class {@link ScrapingServiceException}
	 * 
	 * @throws ScrapingServiceException
	 */
	@Test(expected = ScrapingServiceException.class)
	public void testScrapingServiceWronlUrl() throws ScrapingServiceException {
		
		RequestGenerator request = EasyMock.createMock(RequestGenerator.class);
		Transformation trans = EasyMock.createMock(Transformation.class);
		
		EasyMock.expect(request.getUrl())
			.andReturn("http://www.minijuegdd2323dos.com").anyTimes();
		EasyMock.expect(request.getUserAgent())
			.andReturn("Mozilla Firefox 3.2.1.2.3").anyTimes();
		EasyMock.expect(request.getCookies())
			.andReturn(null).anyTimes();
		EasyMock.expect(request.getVerb()).andReturn(RequestVerb.GET).anyTimes();
		
		sService.scrap(request, trans);
		
	}

}

