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

import com.monits.scraper.RequestGenerator;
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
	 * This method resolves a HTTP request and obtains the String of the HTML Code,
	 * that will be shown in a determinated output.
	 *
	 * @param rGen Interface to manage HTTP Parameters of the Requests.
	 * @param transform Defines the output in order to show the scrap done.
	 *
	 * @return Returns a String with the Scraped HTML
	 * @throws Exception In case of unexpected exception
	 */
	public String scrap(RequestGenerator rGen, Transformation transform)
					throws Exception;

}