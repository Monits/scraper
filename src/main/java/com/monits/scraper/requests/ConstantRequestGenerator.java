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
package com.monits.scraper.requests;

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
	private Map<String,String> cookies;

	public ConstantRequestGenerator(String url, RequestVerb verb) {
		this.url = url;
		this.verb = verb;
		this.userAgent = null;
		this.cookies = null;
	}

	public ConstantRequestGenerator(String url, String userAgent, Map<String,String> cookies) {
		this.url = url;
		this.verb = RequestVerb.GET;
		this.userAgent = userAgent;
		this.cookies = cookies;
	}

	public ConstantRequestGenerator(String url,RequestVerb verb, String userAgent, Map<String,String> cookies) {
		this.url = url;
		this.verb = verb;
		this.userAgent = userAgent;
		this.cookies = cookies;
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
	public  Map<String, String> getCookies() {
		return cookies;
	}

/**
 * Sets the needed data to create the cookie.
 *
 * @param cookies The needed data, in a string map.
 */
	public void setCookie(Map<String,String> cookies) {
		this.cookies = cookies;
	}

	/**
	 *
	 * @param cookies
	 */
	public void setCookies(Map<String,String> cookies) {
		this.cookies = cookies;
	}

}
