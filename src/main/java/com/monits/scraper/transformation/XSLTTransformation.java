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
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * The transformation class that uses XSLT.
 * 
 * @author Ricardo Gomez <rgomez@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public class XSLTTransformation implements Transformation {
	
	private static final int DEFAULT_INITIAL_BUFFER_SIZE = 10000;
	
	protected Transformer xslt;
	
	@Override
	public String transform(String xhtml) throws Exception {
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(DEFAULT_INITIAL_BUFFER_SIZE);
		Result result = new StreamResult(outputStream);
		
		Source source = getSource(xhtml);
		
		try {
			xslt.transform(source, result);
		} catch (Exception e) { 
			throw new Exception(e);
		}
		
		String output;
		try {
			output = outputStream.toString("UTF8");
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return output;
	}
	
	/**
	 * Generate a source from a string containing the XHTML.
	 * 
	 * @param xhtml The XHTML to be contained in the source.
	 * 
	 * @return The source created.
	 * 
	 * @throws
	 */
	private Source getSource(String xhtml) throws Exception {
		DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
		docBuildFactory.setValidating(false);
		docBuildFactory.setExpandEntityReferences(false);
		try {
			docBuildFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuildFactory.newDocumentBuilder();
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		InputStream stream;
		try {
			stream = new ByteArrayInputStream(xhtml.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		Document document;
		try {
			document = docBuilder.parse(stream);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return new DOMSource(document);
	}
	
}
