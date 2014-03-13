package com.example.messageclient;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import android.text.Spannable;
import android.text.style.ClickableSpan;

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
	
		BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
		iterator.setText(message);
		int start = iterator.first();
		// breaks string up into words
		for(int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()){
			String possibleWord = message.substring(start, end);
			if( Character.isLetterOrDigit(possibleWord.charAt(0))){
				// found word.
				System.out.println(possibleWord + "(" + start + " , " + end + " )");
				
			}
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
