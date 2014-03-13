package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.messageclient.TextMessage;

public class TextMessageTests {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testOffsets(){
		TextMessage message = new TextMessage("Hi this is my message");
		System.out.println("===================");
		message.getOffsetsFromMessage("Hi this is my message");
		
	}

}
