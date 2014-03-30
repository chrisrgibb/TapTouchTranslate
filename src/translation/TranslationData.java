package translation;

import java.util.ArrayList;

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
		this.createJSONobjects();
	}
	
	public TranslationData(){
		
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
				System.out.println(m.text);
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
		
		return jsonObject.toJSONString();
	}

	
}
