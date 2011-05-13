/**
 * Unit test for XSLTTransformation class.
 * 
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
package com.monits.scraper.transformation;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.monits.scraper.service.ScrapingServiceException;

/**
 * Unit test for XSLTTransformation class.
 * 
 * @author Ricardo Gomez <rgomez@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public class XSLTTransformationTest {
	
	private static final String SCRAP_TEST_XSL = "scrapTest.xsl";
	
	private String originalCode;
	private String transformedCode;
	private String filePath;
	protected XSLTTransformation trans;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Original file path to a valid XSL.
		this.filePath = getClass().getClassLoader().getResource(SCRAP_TEST_XSL).toString();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for XSLTTransformation.transform.
	 * This test should work properly. 
	 */
	@Test
	public void testTransform() throws ScrapingServiceException {
		
		//Example code.
		this.originalCode = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><catalog><cd><title>Empire Burlesque</title><artist>Bob Dylan</artist><country>USA</country>	<company>Columbia</company><price>10.90</price><year>1985</year></cd></catalog>";
		
		//Initializing a XSLTTransformation class object.
		trans = new XSLTTransformation(filePath);		
		
		//Saving the transformed string into transformedCode variable.
		transformedCode = trans.transform(originalCode);
		
		Assert.assertNotNull("Not null object expected.", transformedCode);
	}
	
	/**
	 * This method forces a TransformerException by sending an example code that can't
	 * be transformed because it's not sanitized.
	 */
	@Test(expected = RuntimeException.class)
	public void testTransformTransformer() throws ScrapingServiceException {
		
		//Example non-sanitized code.
		this.originalCode = "<script>/tryitbanner.asp?secid=tryxslt&rnd=\"+Math.random()}</script>";
		
		trans = new XSLTTransformation(filePath);
		
		trans.transform(originalCode);
	}
	
	/**
	 * Test method for XSLTTransformation.transform but using a claspath file.
	 * This test should work properly. 
	 */
	@Test
	public void testTransformFromClasspath() throws ScrapingServiceException {
		
		//Example code.
		this.originalCode = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><catalog><cd><title>Empire Burlesque</title><artist>Bob Dylan</artist><country>USA</country>	<company>Columbia</company><price>10.90</price><year>1985</year></cd></catalog>";
		
		//Initializing a XSLTTransformation class object.
		trans = new XSLTTransformation("classpath:" + SCRAP_TEST_XSL);		
		
		//Saving the transformed string into transformedCode variable.
		transformedCode = trans.transform(originalCode);
		
		Assert.assertNotNull("Not null object expected.", transformedCode);
	}
	
	/**
	 *  This test forces URISyntaxException by sending an invalid URI supported
	 *  character in the URI path.
	 */
	@Test(expected = RuntimeException.class)
	public void testTransformUriSyntax() throws ScrapingServiceException {
		
		//File path with an unsupported URI character.
		trans = new XSLTTransformation("file:///home/fherrera/workspace/^scrapTest.xsl");
		
	}
	
	/**
	 * This test forces the FileNotFoundException by specifying
	 * a false path to the XSL file that will be used for the transformation.
	 */
	@Test(expected = RuntimeException.class)
	public void testTransformFileNotFound() throws ScrapingServiceException {
		
		//File doesn't exist, so does the file path.
		trans = new XSLTTransformation("file:///home/false.xsl");
	}
	
	/**
	 * This test forces the TransformerConfigurationException by specifying an
	 * invalid XSL file.
	 */
	@Test(expected = RuntimeException.class)
	public void testTransformTransformerConfiguration() throws ScrapingServiceException {
		
		this.filePath = getClass().getClassLoader().getResource("crapTest.xsl").toString();
		
		//XSL file badly coded.
		trans = new XSLTTransformation(filePath);
	}
	
}
