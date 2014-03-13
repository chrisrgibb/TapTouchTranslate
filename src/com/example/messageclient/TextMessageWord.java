package com.example.messageclient;

public class TextMessageWord {

	private String word;
	private int start;
	private int end;
	
	public TextMessageWord(String word, int startOffset, int endOffset){
		this.word = word;
		this.start = startOffset;
		this.end = endOffset;
	}
	
	@Override
	public String toString(){
		return word + "(" + start + "," + end + ")";
	}
	
	public String getWord(){
		return word;
	}
	
	
	
	public boolean isInBounds(int offset){
		return offset >= start && offset < end;
	}
		
}