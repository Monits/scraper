/**
 * Class Identity for Transformation object
 * 
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
package com.monits.scraper.transformation;

/**
 * Class Identity for Transformation object.
 * 
 * @author Fernando Herrera <fherrera@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public class Identity implements Transformation {

	public String transform(String xhtml) {		
		return xhtml;
	}

		
}
