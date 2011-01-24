package com.monits.scraper;

/**
 * 
 *
 * Class whit the constant of the RequestGenerator Interface
 * 
 * @author ndebernardi <ndebernardi@monits.com>
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class ConstantRequestGenerator implements RequestGenerator {


	private String verb;
	private String url;
	private String userAgent;
	private String session;
	private String cookie;
	
	  
	 
	public ConstantRequestGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	  
	
	
	/* (non-Javadoc)
	 * @see com.monits.scraper.RequestGenerator#getVerb()
	 * @return verb object  
	 */
	@Override
	public String getVerb() {
		return verb;
	}
    
	/**
	 * 
	 * @param verb
	 */
	public void setVerb(String verb) {
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
 * 
 * @param url
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
 * 
 * @param userAgent
 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/* (non-Javadoc)
	 * @see com.monits.scraper.RequestGenerator#getSession()
	 * @return session object
	 */
	@Override
	public String getSession() {
		return session;
	}
/**
 * 
 * @param session
 */
	public void setSession(String session) {
		this.session = session;
	}

	/* (non-Javadoc)
	 * @see com.monits.scraper.RequestGenerator#getCookie()
	 * @return cookie object
	 */
	@Override
	public String getCookie() {
		return cookie;
	}
/**
 * 
 * @param cookie
 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	
	
	

}
