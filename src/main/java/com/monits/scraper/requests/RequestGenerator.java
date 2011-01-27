/**
 * Request Generator interface, needed to get the elements
 * for the generation of the HTTP request.
 * 
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.scraper.requests;

import java.util.Map;

/**
 * Request Generator interface, needed to get the elements
 * for the generation of the HTTP request.
 * 
 * @author ndebernardi <ndebernardia@monits.com>
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public interface RequestGenerator {
	
	/**
	 * HTTP Verb getter.
	 * 
	 * @Obtain the verb from the request 
	 * @return  RequestVerb
	 */
	public RequestVerb getVerb();

	/**
	 * Gets the URL.
	 * 
	 * @Obtain the url from the request 
	 * @return String
	 */
	public String getUrl();

	/**
	 * Gets the user agent.
	 * 
	 * @Obtain the userAgent from the request
	 * @return String
	 */
	public String getUserAgent();

	/**
	 * Gets the cookie data.
	 * 
	 * @Obtain the cookie from the request
	 * @return String
	 */
	public Map<String, String> getCookies();

}