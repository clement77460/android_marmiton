package fr.clement.parserWidget;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlDocument {
	private final DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document document;
	
	public XmlDocument() {
		this.factory = DocumentBuilderFactory.newInstance();
		this.createDocumentBuilder();
	}
	
	private void createDocumentBuilder() {
		try 
		{
		  this.builder = this.factory.newDocumentBuilder();
		}
		catch (final ParserConfigurationException e) 
		{
		  e.printStackTrace();
		}
	}
	
	public void readXML(String xmlName) {
		try {
			this.document= builder.parse(new File(xmlName));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Document getDocument() {
		return this.document;
	}
}
