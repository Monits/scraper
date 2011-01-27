/**
 * Identity test for Implemented Transform Interface
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
 * Identity test for Implemented Transform Interface
 * 
 * @author Fernando Herrera <fherrera@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public class IdentityTest {
	
	private String expectedString;
	private String actualString;

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
	 * Test method for Identity.transform(String xhtml).
	 */
	@Test
	public void testTransform() {
		//Everything here should work perfectly.
		
		//Initializing Identity class object.
		Identity id = new Identity();
		
		//Check empty string.
		this.expectedString = "";		
		this.actualString = id.transform(expectedString);
		Assert.assertEquals("Empty string expected.", expectedString, actualString);
		
		//Check common symbols.
		this.expectedString = "{}[]!@/$<>%&()=";		
		this.actualString = id.transform(expectedString);
		Assert.assertEquals("Empty string expected.", expectedString, actualString);
		
		//Check null.
		this.expectedString = null;
		this.actualString = id.transform(expectedString);
		Assert.assertEquals("Null string expected.", expectedString, actualString);
	}
	
}