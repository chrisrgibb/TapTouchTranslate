package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.messageclient.TextMessage;
import com.example.messageclient.TextMessageWord;

public class TextMessageTests {


	@Test
	public void testOffsets(){
		TextMessage message = new TextMessage("Hi this is my message");
		System.out.println("===================");
		assertEquals(message.getWordAtIndex(0), "Hi");
		assertEquals(message.getWordAtIndex(1), "Hi");
		assertEquals(message.getWordAtIndex(2), "");
		assertEquals(message.getWordAtIndex(8), "is");
		
	//	message.getOffsetsFromMessage("Hi this is my message");
		
	}
	
	@Test
	public void testTextMessageWord(){
		// create a new word an test the boundaries of it
		TextMessageWord tmw = new TextMessageWord("this", 0, 4);
		
		for(int i = 0; i< 4; i++){
			assertTrue(tmw.isInBounds(i));
		}
		assertFalse(tmw.isInBounds(4));
		assertFalse(tmw.isInBounds(5));
		
		TextMessageWord tmw2 = new TextMessageWord("Hi", 0, 2);
		assertFalse(tmw2.isInBounds(2));
		
	}
	
	@Test
	public void testStartIndexOfWords(){
		TextMessage tm = new TextMessage("Hi there this is my sweet sweet message");
		tm.printMessage();
		System.out.println(tm.getWordAtIndex(10));
		assertEquals(tm.getStartOfWordAtIndex(10), 9);
		assertEquals(tm.getEndOfWordAtIndex(10), 13);
		
	}

}
