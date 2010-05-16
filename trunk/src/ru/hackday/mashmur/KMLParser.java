package ru.hackday.mashmur;

import java.io.InputStream;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class KMLParser extends DefaultHandler {

	XMLReader xmlReader; 

	public LinkedList<Poi> mPois = new LinkedList<Poi>();
	
	private Poi p;
	private String thisElement = "";
	private InputStream mIs;
	
	KMLParser(InputStream is) {
		mIs = is;
	}

	public void parse() {
		try {
			SAXParserFactory spfactory = SAXParserFactory.newInstance();
			spfactory.setValidating(false);
			SAXParser saxParser = spfactory.newSAXParser();
			saxParser.parse(mIs, this);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		thisElement = localName;
		if (localName.equals("Placemark")) {
			p = new Poi(); 
			mPois.add(p);
		} 
	} 

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String s = new String(ch, start, length);
		if (!s.trim().equals("")) {
		    if (thisElement.equals("name")) {
				p.setName(s);
			} else if (thisElement.equals("description")) {
				p.setDescription(s);
			} else if (thisElement.equals("coordinates")) {
				String[] ll = s.split(",");
				float lat = Float.parseFloat(ll[0]);
				float lon = Float.parseFloat(ll[1]);
				p.setLatitudeE6((int) (lat * Poi.E6));
				p.setLongitudeE6((int) (lon * Poi.E6));
			} else if (thisElement.equals("value")) {
				p.setAudioUrl(s);
			}
		    Log.d("#### Got value: ", s);
		}
	}
	
	

}

