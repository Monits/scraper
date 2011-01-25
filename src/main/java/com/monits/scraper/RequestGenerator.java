
package com.monits.scraper;
/**
 * 
 *
 * Get type of elements necesaries to generate a request.
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
 * @Obtain the verb from the request 
 * @return  String
 */
	public String getVerb();

	/**
	 * @Obtain the url from the request 
	 * @return String
	 */
	public String getUrl();

	/**
	 * @Obtain the userAgent from the request
	 * @return String
	 */
	public String getUserAgent();

	/**
	 * @Obtain the session from the request
	 * @return String
	 */
	//public String getSession();

	/**
	 * @Obtain the cookie from the request
	 * @return String
	 */
	public String getCookie();

}