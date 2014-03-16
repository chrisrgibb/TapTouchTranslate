package com.example.messageclient;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class TranslatorMachine {
	
	private String clientId = "1010Leviathon";
	private String secret = "9ywA/CwiC28RtrHilTetcWfu6LAhjHXCOXecWpjZ6iM=";
	public TranslatorMachine(){

		Translate.setClientId(clientId);
		Translate.setClientSecret(secret);
		System.out.println("translate");
		
	}
	
	public static void main(String[] args) {
		
		TranslatorMachine tm = new TranslatorMachine();
		try {
			String myString = tm.translate("Hello");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String translate(String text) throws Exception{
		String str = "";
		str = Translate.execute(text, Language.CHINESE_SIMPLIFIED);
		
		return str;
	}
	
	
}
