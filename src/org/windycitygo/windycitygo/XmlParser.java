package org.windycitygo.windycitygo;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.windycitygo.windycitygo.handlers.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import org.windycitygo.windycitygo.model.*;

import android.util.Log;

public class XmlParser {
	private static final String CLASSTAG = XmlParser.class.getSimpleName();
	
    private XMLReader initializeReader() throws ParserConfigurationException, SAXException {
    	
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // create a parser
        SAXParser parser = factory.newSAXParser();
        // create the reader (scanner)
        XMLReader xmlreader = parser.getXMLReader();
        return xmlreader;
    }
    
    public ArrayList<SessionCategory> parseSessionResponse(InputStream xml) {
    	Log.v(Constants.LOGTAG, " " + XmlParser.CLASSTAG + " parseSessionResponse" );
        
    	try {		
			XMLReader xmlreader = initializeReader();
			
			SessionHandler sessionHandler = new SessionHandler();
			
			// assign our handler
			xmlreader.setContentHandler(sessionHandler);
			// perform the synchronous parse
			xmlreader.parse(new InputSource(xml));
			
			return sessionHandler.retrieveSessionList();            
        } 
        catch (Exception e) {
        	android.util.Log.e(Constants.LOGTAG, e.getMessage(), e.getCause());
            e.printStackTrace();
            return null;
        }   
    }

	public ArrayList<SponsorLevel> parseSponsorResponse(InputStream xml) {
		Log.v(Constants.LOGTAG, " " + XmlParser.CLASSTAG + " parseSponsorResponse" );
		
		try {		
			XMLReader xmlreader = initializeReader();
			
			SponsorHandler handler = new SponsorHandler();
			
			// assign our handler
			xmlreader.setContentHandler(handler);
			// perform the synchronous parse
			xmlreader.parse(new InputSource(xml));
			
			return handler.retrieveSponsorLevels();            
        } 
        catch (Exception e) {
        	android.util.Log.e(Constants.LOGTAG, e.getMessage(), e.getCause());
            e.printStackTrace();
            return null;
        }
	}

	public ArrayList<Location> parseLocationResponse(InputStream xml) {
		Log.v(Constants.LOGTAG, " " + XmlParser.CLASSTAG + " parseLocationResponse" );
		
		try {		
			XMLReader xmlreader = initializeReader();
			
			LocationHandler handler = new LocationHandler();
			
			// assign our handler
			xmlreader.setContentHandler(handler);
			// perform the synchronous parse
			xmlreader.parse(new InputSource(xml));
			
			return handler.retrieveLocationList();            
        } 
        catch (Exception e) {
        	android.util.Log.e(Constants.LOGTAG, e.getMessage(), e.getCause());
            e.printStackTrace();
            return null;
        }
	}
}