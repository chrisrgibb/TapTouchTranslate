package com.chrisrgibb.translate;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TranslationGroup {

	private String originalphrase;
	private String originalLanguage;
	private String destinationLanguage;
	private JSONObject jsonObject;
	private ArrayList<Translation> translations = new ArrayList<Translation>();
	
	/**
	 * A translationGroup object represents the collections of data returned from the translation API.
	 * It holds a list of translations which each have a phrase and a meaning 
	 * @param jsonObject
	 */
	public TranslationGroup(JSONObject jsonObject){
		this.jsonObject =jsonObject;	
		this.originalphrase = (String) jsonObject.get("phrase");
		this.originalLanguage = (String) jsonObject.get("from" );
		this.destinationLanguage =(String) jsonObject.get("dest");
		this.createJSONobjects();
	}
	
	public TranslationGroup(){
		
	}
	
	public String getPhrase(){
		return this.originalphrase;
	}
	
	public String getDestinationLanguage(){
		return this.destinationLanguage;
	}
	
	public void addPhrase(String phrase){
		this.originalphrase = phrase;
	}
	
	public void addTranslation(ArrayList<Translation> translation){
		this.translations = translation;
	}
	
	public String getOriginalLanguage(){
		return this.originalLanguage;
	}
	
	
	
	/**
	 * returns the  meaning selected from the first index
	 * @param index
	 * @return
	 */
	 
	public String getMeaning(int index){
		// TODO make a better system for retreiving meanings
		String text = translations.get(index).getMeanings().get(index).text;		
		return text;
	}
	
	
	private void createJSONobjects(){
		//if(this.jsonObject instanceOf)
		int containsCount = 0;
		if(this.jsonObject.isEmpty()){
			return ;
		}
		
		JSONArray tuc = (JSONArray) this.jsonObject.get("tuc");
		for(Object o : tuc.toArray()){
			
			JSONObject transJSON = (JSONObject)o;
			
			if(transJSON.containsKey("phrase") && transJSON.containsKey("meanings") ){
				Translation transt = new Translation();
				
				// get meanings
				JSONArray meanings = (JSONArray) transJSON.get("meanings");
				for(Object m : meanings){
					JSONObject mm = (JSONObject)m;
					
					String meaningText = (String) mm.get("text");
					String meaningLang = (String) mm.get("language");
	
					transt.meanings.add(new Meaning(meaningText, meaningLang));
				}
				
				// get phrases
				JSONObject phraseJSON = (JSONObject)transJSON.get("phrase");
				String text = (String) phraseJSON.get("text");
				String lang = (String) phraseJSON.get("language");
				Phrase phrase = new Phrase(text, lang);
				transt.addPhrase(phrase);
				this.translations.add(transt);
				
				
				containsCount++;
			}
		
		//	this.translations.add(translationJAVA);
		}
//		System.out.println("contains count = " +containsCount );
	}
	
	public ArrayList<Translation> getTranslations(){
		return this.translations;
	}
	
	public String getFirstAvailablePhrase(){
		for(Translation t : translations){
			if(t.getPhrase()!=null){
				return t.getPhrase().toString();
			}
		}
		return "No Translation Available";
	}
	
	public String getFirstAvailableMeaning(){
		for(Translation t: translations){
			for(Meaning m : t.meanings){
				return m.text;
//				System.out.println(m.text);
			}
		}
		return "No Meaning Available";
	}
	
	
	public void printprintprint(){
		for(Translation t: translations){
			System.out.println(t.toString());
		}
		
	}
	
	
	@Override
	public String toString(){
		return this.getFirstAvailablePhrase();
	}

	
}
