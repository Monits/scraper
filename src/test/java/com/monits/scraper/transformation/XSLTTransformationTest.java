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
	
	private String originalCode;
	private String transformedCode;

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
	 * Test method for XSLTTransformation.transform.
	 */
	@Test
	public void testTransform() {
		
		//Example html code.
		originalCode = "<span title=\"Hola\">hola mundo</span>";
		
		String filePath = getClass().getClassLoader().getResource("scrapTest.xsl").toString();
		
		//Initializing a XSLTTransformation class object.
		XSLTTransformation trans;
		try {
			trans = new XSLTTransformation(filePath);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		//Saving the transformed string into aCode variable.
		try {
			transformedCode = trans.transform(originalCode);	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
		
		Assert.assertNotNull("Not null object expected.", transformedCode);
		
	}

}
