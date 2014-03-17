package translation;

import org.json.JSONException;
import org.json.simple.JSONObject ;

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
}
