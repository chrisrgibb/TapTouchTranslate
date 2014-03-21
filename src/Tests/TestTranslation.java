package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.junit.Test;

import translation.CountryCodes;
import translation.Translation;
import translation.TranslationData;
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
		// from Api Guidelines
		String from = "pol";
		String dest = "eng";
		String phrase = "witaj";
		TranslationHttpClient client = new TranslationHttpClient();
	
		client.translateAFew(from, dest, phrase);// TODO change this to get return the translation object from 
		
		TranslationData t = new TranslationData( client.getObject());
		assertNotNull(t);
		assertEquals(t.getPhrase(), "witaj");
		assertEquals(t.getDestinationLanguage(), "eng");
		System.out.println("==========");
		assertNotNull(t.getMeaning(0), "greeting");
		assertEquals(t.getMeaning(0), "greeting");
	}
	
	@Test
	public void testSpanish(){
		String dest = CountryCodes.SPANISH;
		String from = CountryCodes.ENGLISH;
		String phrase = "hello";
		TranslationHttpClient client = new TranslationHttpClient();

		TranslationData data = client.translateAFew(from, dest, phrase);
		
		
		assertNotNull(data);
		
		assertTrue(data.getTranslations().size()  > 0 );
		

		
	}
	
	@Test
	public void testNullObject(){
		TranslationHttpClient client = new TranslationHttpClient();
		JSONObject nulob = client.getNullObject();
		assertNotNull(nulob);
	}
	

	



}
