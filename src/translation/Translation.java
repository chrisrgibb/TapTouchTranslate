package translation;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Translation {

	private String originalphrase;
	private String destinationLanguage;
	private String meaning;
	private JSONObject jsonObject;
	
	public Translation(JSONObject jsonObject){
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
	
	public String getMeaning(int index){
		// TODO need to do something about this!!
		JSONArray tuc = (JSONArray) this.jsonObject.get("tuc"); // not sure what tuc means
		int numberOfAuthors = tuc.size();
		JSONObject firstEntry = (JSONObject) tuc.get(0);
		JSONArray meanings = (JSONArray) firstEntry.get("meanings");
		String text = (String) ((JSONObject) meanings.get(0)).get("text");
		return text;
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
		Translation t = new Translation( client.getObject());		
	}
	
}
