/**
 * Sanitation Interface
 *
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.scraper.sanitation;


/**
 * Sanitation Interface
 *
 * @author José Rolón <jmrolon@monits.com>
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public interface Sanitation {

	/**
	 * Sanitation method.
	 *
	 * @param html A String with raw html code.
	 * @return A string with sanitized html
	 */
	public String sanitize(String html) throws Exception;

}
