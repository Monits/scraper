/**
 * Interface For Transformation object,
 * allowing the user to transform the scraped xhtml
 * into whatever they like.
 * 
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
package com.monits.scraper.transformation;

/**
 * Interface For Transformation object,
 * allowing the user to transform the scraped xhtml
 * into whatever they like.
 * 
 * @author Fernando Herrera <fherrera@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public interface Transformation {
	
	/**
	 * Transform the xhtml into the desired output.
	 * 
	 * @param xhtml The xhtml to transform.
	 * 
	 * @return The transformed xhtml.
	 */
	public String transform(String xhtml);
	
}
