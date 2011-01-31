/**
 * Sanitation
 *
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.scraper.sanitation;

import java.io.Reader;
import java.io.StringReader;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.CompactXmlSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


/**
 * Sanitation using HtmlCleaner
 *
 * @author José Rolón <jmrolon@monits.com>
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class SanitationHtmlCleaner implements Sanitation {

	@Override
	public String sanitize(String html) throws Exception {
		
		HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties props = cleaner.getProperties();
        props.setOmitXmlDeclaration(true);
        TagNode node = cleaner.clean(html);
        String result = new CompactXmlSerializer(cleaner.getProperties()).getAsString(node);
        getJDomOutput(result);
        
		return  getJDomOutput(result);
		
	}
	
	private String getJDomOutput(Reader reader) throws Exception {
		
        SAXBuilder saxBuilder = new SAXBuilder();
        final Document document;
        document = saxBuilder.build(reader);
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getCompactFormat());
        return xmlOutputter.outputString(document);
      
    }

    private String getJDomOutput(String xml) throws Exception {
    	
        return getJDomOutput(new StringReader(xml));
        
    }

	
}
