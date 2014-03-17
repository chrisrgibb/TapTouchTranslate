package translation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//import org.json.JSONException;
//import org.json.JSONObject;
import org.json.simple.*;;

public class TranslationHttpClient {
	String translation;
	
	public TranslationHttpClient(){
		 translation = getTranslation();
		doTranslation();
	}
	
	public JSONObject getObject(){
		JSONObject jObj ;

		jObj = (JSONObject) JSONValue.parse(translation);
		return jObj;
	}
	
//	public Translation getObject(){
//		
//		return new Translation(getObject(translation));
//	}
//	
//	public Translation translatePhrase(String phrase){
//		
//		
//		return new Translation(new JSONObject());
//	}
	
	public String composeURL(String from, String dest, String phrase){
		StringBuffer sb = new StringBuffer();
		sb.append("http://glosbe.com/gapi/translate?from=");
		sb.append(from);
		sb.append("&dest=" + dest);
		sb.append("&format=json&phrase=");
		sb.append(phrase);
		sb.append("&pretty=true");
		return sb.toString();	
	}
	
	public String translateAFew(String from, String dest, String phrase){

		String url = this.composeURL(from, dest, phrase);
		
		try {
			HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();
			
			StringBuffer buffer = new StringBuffer();
			InputStream is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
            while (  (line = br.readLine()) != null ){
                buffer.append(line + "\r\n");
            }
 
            is.close();
            con.disconnect();

            translation = buffer.toString();
	
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}
	
	private void doTranslation(){
		String url = "http://glosbe.com/gapi/translate?from=pol&dest=eng&format=json&phrase=witaj&pretty=true";
		//String url = "http://glosbe.com/gapi/translate?from=pol&dest=eng&format=json&phrase=witaj&pretty=true";
		
		try {
			HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();
			
			StringBuffer buffer = new StringBuffer();
			InputStream is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
            while (  (line = br.readLine()) != null ){
                buffer.append(line + "\r\n");
            }
 
            is.close();
            con.disconnect();

            translation = buffer.toString();
	
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getTranslation(){
		if(translation==null){
			return "no translation";
		}else{
			return translation;
		}
	}
	

}
