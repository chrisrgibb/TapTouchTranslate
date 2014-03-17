package Tests;

import org.junit.Test;
import translation.Translation;
import translation.TranslationHttpClient;


public class TestTranslation {
	
	
	@Test
	public void testTranslation(){
		TranslationHttpClient client = new TranslationHttpClient();
//		Translation t = client.getObject();
		System.out.println(client.getTranslation());
		
	}

}
