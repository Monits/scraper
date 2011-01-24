/**
 * Interface Trans
 * 
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
package com.monits.scraper.transformation;

/**
 * Interface Trans
 * 
 * @author Fernando Herrera <fherrera@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public interface Trans {
	
	/**
	 * Transform the xhtml object into the specified output type.
	 * (Subject to changes)
	 * 
	 * @param xhtmlObject The requested xhtml.
	 * 
	 * @param trans The specified output type.
	 * 
	 * @return The transformed object.
	 */
	public abstract String generateOutput(String xhtmlObject, String trans);
	
}
