package com.example.messageclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TextMessage {

//	ArrayList<TextMessageWord> words;
	HashMap<String, TextMessageWord> words;
	List<TextMessageWord> wordsList = new LinkedList<TextMessageWord>();
	
	public TextMessage(){
		this.words = new HashMap<String, TextMessageWord>();
	}
	
	public TextMessage(String sentance){
		this.words = new HashMap<String, TextMessageWord>();
		String[] listOfWords = sentance.split("\\s+");
		int offset = 0;
		for(String s : listOfWords){
			// TODO need to fix offset 
			offset = sentance.indexOf(s, offset);
			int end = offset + s.length();
			words.put(s ,new TextMessageWord(s, offset, end));
			wordsList.add(new TextMessageWord(s, offset, end));
			System.out.print(s);
			System.out.println(" ("+ offset + "," + end + ")");
		}
	}
	
	public boolean addWord(String word,int startOffset, int endOffset){
		if(!words.containsKey(word)){
			words.put(word ,new TextMessageWord(word, startOffset, endOffset));
			wordsList.add(new TextMessageWord(word, startOffset, endOffset));
			
			return true;
		}else{
			return false;
		}
	}
	
	public void getOffsetsFromMessage(String message){
		// TODO steal code from WriteMessageActivity BreakIterator
		char[] charArray = message.toCharArray();
		int length = charArray.length;
		int index = 0;
		int wordOffset = 0;
		while(index <= length){
			if(Character.isLetterOrDigit(charArray[index])){
				index++;
				
			}
			if(Character.isSpaceChar(charArray[index]) ){
				System.out.println(message.substring(wordOffset, index));
				System.out.println(index==length);
				index++;
			}
//			if(charArray[index])
		}
		
		
		
	}
	
	
	public String getWord(int offset){
		for(TextMessageWord w : wordsList){
			
			
		}
		
		return "";
	}
	
	public HashMap<String, TextMessageWord> getWords(){
		return this.words;
	}
	
	public void printMessage(){
		for(TextMessageWord t: this.wordsList){
			System.out.println(t.toString());
		}
	}
	
}
