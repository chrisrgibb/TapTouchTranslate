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


	List<TextMessageWord> wordsList = new LinkedList<TextMessageWord>();
	
	public TextMessage(){
	
	}
	
	public TextMessage(String message){
		getOffsetsFromMessage(message);
		
	}
	
	public boolean addWord(String word,int startOffset, int endOffset){
		
			return false;
	
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
				TextMessageWord tmw = new TextMessageWord(possibleWord, start, end);
				wordsList.add(tmw);
			}
		}	
	}
	
	
	public String getWordAtIndex(int offset){
		for(TextMessageWord w : wordsList){
			if(w.isInBounds(offset)){
				return w.getWord();
			}
		}
		return "";
	}
	
	public void printMessage(){
		for(TextMessageWord t: this.wordsList){
			System.out.println(t.toString());
		}
	}
	
}
