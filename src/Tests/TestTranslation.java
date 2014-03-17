package Tests;

import static org.junit.Assert.*;

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
		
		
	}

}
