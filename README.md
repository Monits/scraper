# Monits's Scraper

This is the scraping framework we use at Monits. It splits the scraping into 3 different stages:

* Generation of the request to obtain the scraped resource
* Sanitization of the scraped resource
* Retrieving the desired data in the desired format (scraping itself)

Each of this stages can be configured and customized at will. We provide basic implementations for the interfaces in each layer, but you are free to write your own.

Right now the default usage is:
- Constant settings for requesting the page are set (HTTP verb to be used, headers, cookies, url, so on)
- [HtmlCleaner](http://htmlcleaner.sourceforge.net/) is used to sanitize the HTML (by far the best tool we could find for the job, awesome work guys!)
- Finally, XSLT is used to transform the now standard HTML into any desired output XML format.

The project is in use for several different projects at Monits, and therefore we are allways looking for ways to improve it.

# Maven users!

The project is hosted in our Open Source Maven repository!

	<repositories>
		<repository>
			<id>monits-snapshots</id>
			<url>http://nexus.monits.com/content/repositories/oss-snapshots/</url>
			<name>Monits Snapshots</name>
		</repository>
	</repositories>

	<dependencies>
		<dependency>
			<groupId>com.monits</groupId>
			<artifactId>scraper</artifactId>
			<version>1.2-SNAPSHOT</version>
		</dependency>
	</dependencies>

# Installation

Just

>
> mvn clean install
>

# Contributing

We encourage you to contribute to this project! 

We are also looking forward to your bug reports, feature requests and questions regarding our Scraper.

# Copyright and License

Copyright 2011 Monits.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License at: 

http://www.apache.org/licenses/LICENSE-2.0


