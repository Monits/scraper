/**
 * Scraping Service Exception
 *
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.scraper.service;

/**
 * Scraping Service Exception
 *
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public final class ScrapServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Scraping Service Exception constructor.
	 *
	 * @param cause The cause (which is saved for later retrieval
	 * by the Throwable.getCause() method).
	 * (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public ScrapServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Scraping Service Exception constructor.
	 *
	 * @param message The detail message
	 * (which is saved for later retrieval by the Throwable.getMessage() method)
	 */
	public ScrapServiceException(String message) {
		super(message);
	}

	/**
	 * Scraping Service Exception constructor.
	 *
	 * @param message The detail message
	 * (which is saved for later retrieval by the Throwable.getMessage() method)
	 *
	 * @param cause The cause (which is saved for later retrieval
	 * by the Throwable.getCause() method).
	 * (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public ScrapServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Scraping Service Exception constructor with null message.
	 */
	public ScrapServiceException() {
		super();
	}

}
