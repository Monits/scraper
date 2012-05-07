/**
 * Request Generator interface, generates the parameters for HTTP request.
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
 * Request Generator interface, generates the parameters for HTTP request.
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
	 * Request's HTTP Verb getter.
	 *
	 * @return The verb from the generated request.
	 */
	public RequestVerb getVerb();

	/**
	 * Add a new header given the key and value.
	 * 
	 * @param key
	 * @param value
	 */
	public void addHeader(String key, String value);

	/**
	 * Retrieve the request's headers.
	 * 
	 * @return map contaning the headers.
	 */
	public Map<String, String> getHeaders();

	/**
	 * Request's URL getter.
	 *
	 * @return a String with the url from the generated request.
	 */
	public String getUrl();

	/**
	 * Request's User-Agent getter.
	 *
	 * @return A String with the userAgent from the generated request.
	 */
	public String getUserAgent();

	/**
	 * Request's Cookies getter.
	 *
	 * @return a Map with all the cookies from the generated request.
	 */
	public Map<String, String> getCookies();

	/**
	 * Request's body getter.
	 *
	 * @return a Map with the body from the generated request.
	 */
	public Map<String, String> getBody();

}