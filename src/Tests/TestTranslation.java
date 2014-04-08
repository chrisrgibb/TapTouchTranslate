package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.junit.Test;

import translation.TranslationGroup;
import translation.TranslationHttpClient;


public class TestTranslation {
	
	String ENGLISH = "eng";
	String SPANISH = "spa";
	String CHINESE = "cmn";
	String ITALIAN = "ita";

	
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
	
		client.translateAFew(from, dest, phrase);
		
		TranslationGroup t = new TranslationGroup( client.getObject());
		assertNotNull(t);
		assertEquals(t.getPhrase(), "witaj");
		assertEquals(t.getDestinationLanguage(), "eng");
		System.out.println("==========");
		assertNotNull(t.getMeaning(0), "greeting");
		assertEquals(t.getMeaning(0), "greeting");
	}
	
	@Test
	public void testSpanish(){
		String dest = "spa";
		String from = "eng";
		String phrase = "hello";
		TranslationHttpClient client = new TranslationHttpClient();

		TranslationGroup data = client.translateAFew(from, dest, phrase);
			
		assertNotNull(data);
		
		assertTrue(data.getTranslations().size()  > 0 );
		System.out.println("STRIng = "  + data.getTranslations().get(0).getPhrase().toString());

	}
	
	@Test
	public void testItalian(){
		String dest = ITALIAN;
		String from = ENGLISH;
		String phrase = "hello";
		TranslationHttpClient client = new TranslationHttpClient();

		TranslationGroup data = client.translateAFew(from, dest, phrase);
			
		assertNotNull(data);
		
		assertTrue(data.getTranslations().size()  > 0 );
		System.out.println("STRIng = "  + data.getTranslations().get(0).getPhrase().toString());

	}
	
	@Test
	public void testGetFirstAvailablePhrase(){
		String from = ENGLISH;
		String dest = SPANISH;
		String phrase = "hello";
		TranslationHttpClient client = new TranslationHttpClient();

		TranslationGroup data = client.translateAFew(from, dest, phrase);
		
		assertNotNull(data.getFirstAvailablePhrase());
		System.out.println("First available phrase " + data.getFirstAvailablePhrase());
	}
	
	@Test
	public void testNullObject(){
		TranslationHttpClient client = new TranslationHttpClient();
		JSONObject nulob = client.getNullObject();
		assertNotNull(nulob);
	}


	



}
