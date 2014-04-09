package com.chrisrgibb.taptouchtranslate;

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
	
	public int getStartOffset(){
		return start;
	}
	
	public int getEndOffset(){
		return end;
	}
	
	
	public boolean isInBounds(int offset){
		return offset >= start && offset < end;
	}
		
}
