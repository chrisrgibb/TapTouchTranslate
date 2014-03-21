package Tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.json.simple.JSONObject;
import org.junit.Test;

import translation.Translation;
import translation.TranslationHttpClient;


public class TestTranslation {
	
	

	
	@Test
	public void testURL(){
		TranslationHttpClient client = new TranslationHttpClient();
		String dest = "eng";
		String from = "pol";
		String phrase = "witaj";
		String testURL = "http://glosbe.com/gapi/translate?from=pol&dest=eng&format=json&phrase=witaj&pretty=true";		
		assertEquals(testURL, client.composeURL(from, dest, phrase) );	
	}
	
	@Test
	public void newTranslation(){
		String from = "pol";
		String dest = "eng";
		String phrase = "witaj";
		TranslationHttpClient client = new TranslationHttpClient();
		client.setPhrase(from, dest, phrase);
		client.translateAFew(from, dest, phrase);// TODO change this to get return the translation object from 
		
		Translation t = new Translation( client.getObject());
		assertNotNull(t);
		assertEquals(t.getPhrase(), "witaj");
		assertEquals(t.getDestinationLanguage(), "eng");
		t.printMyString();
		System.out.println(t.getMeaning(0));
		assertEquals(t.getMeaning(0), "greeting");
		//System.out.println(t.toString());
	}
	
	@Test
	public void testNullObject(){
		TranslationHttpClient client = new TranslationHttpClient();
		JSONObject nulob = client.getNullObject();
		assertNotNull(nulob);
	}



}
