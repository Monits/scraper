/**
 * The transformation class that uses XSLT.
 * 
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
package com.monits.scraper.transformation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

/**
 * The transformation class that uses XSLT.
 * 
 * @author Ricardo Gomez <rgomez@monits.com>
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class XSLTTransformation implements Transformation {

	private static final String CLASSPATH_PREFIX = "classpath:";

	private static final int DEFAULT_INITIAL_BUFFER_SIZE = 10000;

	protected Transformer xslt;

	/**
	 * Parameterized constructor, it builds the URI of the XSL that will be used
	 * to transform the retrieved XHTML.
	 * 
	 * @param xslt The path to the XSL file. May start with "classpath:" to reference a file in the current classpath.
	 */
	public XSLTTransformation(String xslt) {

		InputStream stream;
		try {
			// Support the "classpath:" prefix to look in the current classpath
			if (xslt.startsWith(CLASSPATH_PREFIX)) {
				stream = getClass().getClassLoader().getResourceAsStream(xslt.substring(CLASSPATH_PREFIX.length()));
			} else {
				stream = new FileInputStream(new File(new URI(xslt)));
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		Source xsltSource = new StreamSource(stream);
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		try {
			this.xslt = transformerFactory.newTransformer(xsltSource);
		} catch (TransformerConfigurationException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public String transform(String xhtml) {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
				DEFAULT_INITIAL_BUFFER_SIZE);
		Result result = new StreamResult(outputStream);

		Source source = getSource(xhtml);

		try {
			xslt.transform(source, result);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}

		String output;
		try {
			output = outputStream.toString("UTF8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		return output;
	}

	/**
	 * Generate a source from a string containing the XHTML.
	 * 
	 * @param xhtml
	 *            The XHTML to be contained in the source.
	 * @return The source created.
	 * @throws RuntimeException
	 */
	private Source getSource(String xhtml) {
		DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory
				.newInstance();
		docBuildFactory.setValidating(false);
		docBuildFactory.setExpandEntityReferences(false);
		try {
			docBuildFactory
					.setFeature(
							"http://apache.org/xml/features/nonvalidating/load-external-dtd",
							false);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuildFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}

		InputStream stream;
		try {
			stream = new ByteArrayInputStream(xhtml.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		Document document;
		try {
			document = docBuilder.parse(stream);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return new DOMSource(document);
	}
}
