package translation;

import org.json.JSONException;
import org.json.JSONObject;

public class Translation {

	private String originalphrase;
	private String destinationLanguage;
	private String meaning;
	private JSONObject jsonObject;
	
	public Translation(JSONObject jObj){
		jsonObject =jObj;		
	}
	
}
