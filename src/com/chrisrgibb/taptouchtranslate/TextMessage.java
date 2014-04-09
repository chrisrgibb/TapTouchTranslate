package com.chrisrgibb.taptouchtranslate;

import java.text.BreakIterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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
	
	
	/**
	 * takes a string and breaks it up into words 
	 * @param message
	 */
	public void getOffsetsFromMessage(String message){
	
		BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
		iterator.setText(message);
		int start = iterator.first();
		// breaks string up into words
		for(int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()){
			String possibleWord = message.substring(start, end);
			if( Character.isLetterOrDigit(possibleWord.charAt(0))){
				// found word.
//				System.out.println(possibleWord + "(" + start + " , " + end + " )"); 
				TextMessageWord tmw = new TextMessageWord(possibleWord, start, end);
				wordsList.add(tmw);
			}
		}	
	}
	
	/**
	 * 
	 * @param offset
	 * @return the word at that index or an empty string.
	 */
	public String getWordAtIndex(int offset){
		// maybe not that efficient but average size of message won't be big hopefully
		for(TextMessageWord w : wordsList){
			if(w.isInBounds(offset)){
				return w.getWord();
			}
		}
		return "";
	}
	
	public int getWordStartOffset(int index){
		// TODO think of better Name
		for(TextMessageWord w : wordsList){
			if(w.isInBounds(index)){
				return w.getStartOffset();
			}
		}
		return -1;
	}
	
	/**
	 * get the offset of the last char of the word at index
	 * @param index
	 * @return
	 */
	public int getWordEndOffset(int index){
		for(TextMessageWord w : wordsList){
			if(w.isInBounds(index)){
				return w.getEndOffset();
			}
		}
		return -1;
	}

	
	public void printMessage(){
		for(TextMessageWord t: this.wordsList){
			System.out.println(t.toString());
		}
	}
	
}
