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
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
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
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public class XSLTTransformation implements Transformation {
	
	private static final int DEFAULT_INITIAL_BUFFER_SIZE = 10000;
	
	protected Transformer xslt;
	
	public XSLTTransformation(String xslt) throws Exception {
		
		URI xsltUri = new URI(xslt);
		InputStream stream = new FileInputStream(new File(xsltUri));
		
		Source xsltSource = new StreamSource(stream);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		this.xslt = transformerFactory.newTransformer(xsltSource);
		
	}
	
	@Override
	public String transform(String xhtml) throws Exception {
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(DEFAULT_INITIAL_BUFFER_SIZE);
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
	 * @param xhtml The XHTML to be contained in the source.
	 * 
	 * @return The source created.
	 * 
	 * @throws RuntimeException
	 */
	private Source getSource(String xhtml) throws Exception {
		DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
		docBuildFactory.setValidating(false);
		docBuildFactory.setExpandEntityReferences(false);
		try {
			docBuildFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
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
