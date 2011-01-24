/**
 * Scraping Service
 *
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.scraper.service;

/**
 * Scraping Service
 *
 * @author Gaston Muñiz <gmuniz@monits.com>
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public interface ScrapingService{

	/**
	 * This method resolves a HTTP request and gets the String of the HTML Code
	 *
	 * @param req String with the complete HTTP Request
	 * @return Returns a String with the Scraped HTML
	 */
	public abstract String scrap(String req);

}