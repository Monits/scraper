/**
 * Class with the implementation of the RequestGenerator Interface
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
 * Class with the implementation of the RequestGenerator Interface
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

	/**
	 * HTTP Request constructor.
	 *
	 * @param url String that contains HTTP url.
	 * @see {@link RequestVerb}
	 */
	public ConstantRequestGenerator(String url) {
		this.url = url;
		this.verb = RequestVerb.GET;
		this.userAgent = null;
		this.cookies = null;
	}

	/**
	 * HTTP Request constructor.
	 *
	 * @param url String that cointains the http url.
	 * @param verb from {@link RequestVerb} used to define HTTP Action.
	 * @see {@link RequestVerb}
	 */
	public ConstantRequestGenerator(String url, RequestVerb verb) {
		this.url = url;
		this.verb = verb;
		this.userAgent = null;
		this.cookies = null;
	}

	/**
	 * HTTP Request Constructor.
	 *
	 * @param url String that cointains the http url.
	 * @param verb from {@link RequestVerb} used to define HTTP Action.
	 * @param userAgent String that defines browsers range.
	 * @param cookies Map with the cookies.
	 * @see {@link RequestVerb}
	 */
	public ConstantRequestGenerator(String url, RequestVerb verb,
								String userAgent, Map<String,String> cookies) {
		this.url = url;
		this.verb = verb;
		this.userAgent = userAgent;
		this.cookies = cookies;
	}

	@Override
	public  RequestVerb getVerb() {
		return verb;
	}

	/**
	 * Sets the request verb to the generated request.
	 *
	 * @param verb The requested verb.
	 */
	public void setVerb(RequestVerb verb) {
		this.verb = verb;
	}

	@Override
	public String getUrl() {
		return url;
	}

	/**
	 * Sets an URL to the generated request.
	 *
	 * @param url The desired URL.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * Sets the user agent.
	 *
	 * @param userAgent sets an UserAgent to the generated request.
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public  Map<String, String> getCookies() {
		return cookies;
	}

	/**
	 * Sets a map of cookies to the generated request.
	 *
	 * @param cookies Map<String,String> with the cookies.
	 */
	public void setCookies(Map<String,String> cookies) {
		this.cookies = cookies;
	}

}
