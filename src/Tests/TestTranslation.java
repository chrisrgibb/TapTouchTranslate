package Tests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Test;

import translation.Translation;
import translation.TranslationHttpClient;


public class TestTranslation {
	
	
	@Test
	public void testTranslation(){
		TranslationHttpClient client = new TranslationHttpClient();
		Translation t = new Translation( client.getObject());
		System.out.println(client.getTranslation());
		assertEquals(t.getPhrase(), "witaj");
		assertEquals(t.getDestinationLanguage(), "eng");
		//System.out.println(t.);
		
		
		
		
		
	}
	
	@Test
	public void testURL(){
		TranslationHttpClient client = new TranslationHttpClient();
		String dest = "eng";
		String from = "pol";
		String phrase = "witaj";
		String testURL = "http://glosbe.com/gapi/translate?from=pol&dest=eng&format=json&phrase=witaj&pretty=true";
	
		
		assertEquals(testURL, client.composeURL(from, dest, phrase) );

		
	}



}
