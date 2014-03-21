package translation;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TranslationData {

	private String originalphrase;
	private String destinationLanguage;
	private JSONObject jsonObject;
	private ArrayList<Translation> translations = new ArrayList<Translation>();
	
	
	public TranslationData(JSONObject jsonObject){
		this.jsonObject =jsonObject;	
		this.originalphrase = (String) jsonObject.get("phrase");
		this.destinationLanguage =(String) jsonObject.get("dest");
	}
	
	public String getPhrase(){
		return this.originalphrase;
	}
	
	public String getDestinationLanguage(){
		return this.destinationLanguage;
	}
	
	
	public void printMyString(){
		System.out.println("PRINTING STRING");
		// TODO gross testing method that needs to be deleted eventually
		JSONArray ja = (JSONArray) this.jsonObject.get("tuc");
		int numberOfAuthors = ja.size();
		System.out.println(ja.size());
		
		
		JSONObject o = (JSONObject) ja.get(1);
		System.out.println("MEANINGS");
		System.out.println(o.get("meanings").toString());
		// get meanings
		JSONArray meanings = (JSONArray) o.get("meanings");
		int numberOfMeanings = meanings.size();
		System.out.println("Number of meanings = " +numberOfMeanings);
		
		JSONObject meaningOne = (JSONObject) meanings.get(0);
		//JSONObject meaningTwo = (JSONObject) meanings.get(1);
		System.out.println("ITERATOR");
		Iterator<JSONObject> iterator = ja.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
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
	
	
	public void createJSONobjects(){
		//if(this.jsonObject instanceOf)
		if(this.jsonObject.isEmpty()){
			return ;
		}
		
		JSONArray tuc = (JSONArray) this.jsonObject.get("tuc");
		for(Object o : tuc.toArray()){
			
			JSONObject transJSON = (JSONObject)o;
			Translation translationJAVA = new Translation();
			
			if(transJSON.containsKey("meanings")){
				// Iterate through meanings Array
				// May not necessarily have a meanings array
				
				JSONArray meanings = (JSONArray) transJSON.get("meanings");
				for(Object m : meanings){
					JSONObject mm = (JSONObject)m;
					
					String meaningText = (String) mm.get("text");
					String meaningLang = (String) mm.get("language");
					System.out.println(meaningLang);
					translationJAVA.meanings.add(new Meaning(meaningText, meaningLang));
				}
			}
			

			if(transJSON.containsKey("phrase")){
				JSONObject phraseJSON = (JSONObject)transJSON.get("phrase");
				String text = (String) phraseJSON.get("text");
				String lang = (String) phraseJSON.get("language");
				Phrase phrase = new Phrase(text, lang);
				translationJAVA.addPhrase(phrase);
			}
		
			this.translations.add(translationJAVA);
			
		}
		System.out.println(translations);
	}
	
	public void printprintprint(){
		for(Translation t: translations){
			System.out.println(t.toString());
		}
		
	}
	
	
	@Override
	public String toString(){
		
		return jsonObject.toJSONString();
	}
	
	public static void main(String[] args) {
		String from = "pol";
		String dest = "eng";
		String phrase = "witaj";
		TranslationHttpClient client = new TranslationHttpClient();
		client.setPhrase(from, dest, phrase);
		TranslationData t = new TranslationData( client.getObject());		
	}
	
}
