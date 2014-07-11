package com.chrisrgibb.translate;

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
	String destLanguage = "eng";
	String URL;
	
	public TranslationHttpClient(){
		
	}
	
	public JSONObject getNullObject(){
		return new JSONObject();	
	}
	
	public JSONObject getObject(){
		JSONObject jObj ;
		jObj = (JSONObject) JSONValue.parse(translation);
		return jObj;
	}
	
	public void setURL(String url){
		translation = url;
	}
	
	public String composeURL(String from, String dest, String phrase){
		StringBuffer sb = new StringBuffer();
		sb.append("http://glosbe.com/gapi/translate?from=");
		sb.append(from);
		sb.append("&dest=" + dest);
		sb.append("&format=json&phrase=");
		sb.append(phrase.toLowerCase());
		sb.append("&pretty=true");
		URL = sb.toString();
		return URL;	
	}
	
	/**
	 * connects to the glosbe API and returns a translationGroup Object 
	 * containing a collection of Translations 
	 * @param from
	 * @param dest
	 * @param phrase
	 * @return
	 */
	public TranslationGroup translateAFew(String from, String dest, String phrase){

		String url = this.composeURL(from, dest, phrase);
		try {
			HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			//con.setDoOutput(true);
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
            JSONObject o = getObject();
            return new TranslationGroup(o);
	
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// else return a new TranslationData with nothing in it.
		return new TranslationGroup(new JSONObject());
	}
	


	
		

}
