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

import com.monits.scraper.requests.RequestGenerator;
import com.monits.scraper.sanitation.Sanitation;
import com.monits.scraper.transformation.Transformation;

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
public interface ScrapingService {

	/**
	 * This method resolves a HTTP request and retrieves the String of
	 * the HTML Code that will be shown in a determinated output.
	 *
	 * @param rGen Interface to manage HTTP Parameters of the Requests.
	 * @param transform Defines the output in order to show the scrap done.
	 * @return Returns a String with the Scraped HTML
	 * @throws ScrapingServiceException in case of unexpected exception.
	 */
	public String scrap(RequestGenerator rGen, Transformation transform)
		throws ScrapingServiceException;

	/**
	 * Sets a Sanitizer to perform HTML sanitation. HtmlCleaner is set as default.
	 *
	 * @param htmlSanitizer
	 * @see {@link HtmlCleaner}, {@link Sanitation}
	 */
	public void setSanitizer(Sanitation htmlSanitizer);
	/**
	 * Sets the timeout for connections when scraping.
	 *
	 * @param timeout The timeout in ms.
	 */
	public void setTimeout(int timeout);
}