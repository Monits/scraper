/**
 * Class with the constant of the RequestGenerator Interface
 * 
 * @author ndebernardi <ndebernardi@monits.com>
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.scraper;

import java.util.Map;

/**
 * Class with the constant of the RequestGenerator Interface
 * 
 * @author ndebernardi <ndebernardi@monits.com>
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class ConstantRequestGenerator implements RequestGenerator {
	
	private RequestVerb verb;
	private String url;
	private String userAgent;
	private Map<String,String> cookie;
	 
	public ConstantRequestGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see com.monits.scraper.RequestGenerator#getVerb()
	 * @return verb object  
	 */
	@Override
	public  RequestVerb getVerb() {
		return verb;
	}
    
	/**
	 * Sets the request verb.
	 * 
	 * @param verb The requested verb.
	 */
	public void setVerb(RequestVerb verb) {
		this.verb = verb;
	}

	/* (non-Javadoc)
	 * @see com.monits.scraper.RequestGenerator#getUrl()
	 * @return url object
	 */
	@Override
	public String getUrl() {
		return url;
	}
	
/**
 * Sets the URL.
 * 
 * @param url The desired URL.
 */
	public void setUrl(String url) {
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see com.monits.scraper.RequestGenerator#getUserAgent()
	 * @return userAgent object
	 */
	@Override
	public String getUserAgent() {
		return userAgent;
	}
	
/**
 * Sets the user agent.
 * 
 * @param userAgent The needed User Agent.
 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	/* (non-Javadoc)
	 * @see com.monits.scraper.RequestGenerator#getCookie()
	 * @return cookie object
	 */
	@Override
	public  Map<String, String> getCookie() {
		return cookie;
	}
	
/**
 * Sets the needed data to create the cookie.
 * 
 * @param cookie The needed data, in a string map.
 */
	public void setCookie(Map<String,String> cookie) {
		this.cookie = cookie;
	}

}
