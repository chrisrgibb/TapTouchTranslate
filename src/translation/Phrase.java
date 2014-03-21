package translation;

public class Phrase {

	String text;
	String language;
	
	public Phrase(String text, String language){
		this.text = text;
		this.language = language;
	}
	
	@Override
	public String toString(){
		return this.text;
	}
	
}
