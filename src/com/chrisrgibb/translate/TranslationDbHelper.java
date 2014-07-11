package com.chrisrgibb.translate;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TranslationDbHelper extends SQLiteOpenHelper {

	
	public static final String DATABASE_NAME =  "translationDB";
	private static final String DATABASE_TABLE ="translations";
	private static final int DATABASE_VERSION = 4;
	
	
	private static final String KEY_ID     = "id";
	private static final String KEY_PHRASE  = "phrase";
	private static final String KEY_MEANING = "meaning";
	
	private static final String[] COLUMNS = {KEY_ID,KEY_PHRASE,KEY_MEANING};
	
	public TranslationDbHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	public TranslationDbHelper(Context context){
		
		super(context,DATABASE_NAME, null, DATABASE_VERSION );
//		this.printDataBase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("DB ONCREATE");
		String CREATE_TRANSLATION_TABLE = "CREATE TABLE " + DATABASE_TABLE + 
				" ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"phrase TEXT, "+
				"meaning TEXT )";
		
		db.execSQL(CREATE_TRANSLATION_TABLE);
		
	}
	
	public void close(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.close();
	}
	
	public void alterTable(SQLiteDatabase db){
		String alter ="ALTER TABLE " + DATABASE_TABLE + " RENAME TO tmp_table_name"	;
		db.execSQL(alter);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("on upgrade");
		// Drop older TRANSLATIONS table if existed
        db.execSQL("DROP TABLE IF EXISTS translations");
 
        // create fresh books table
        this.onCreate(db);
		
	}
	
	public void addTranslation(TranslationGroup d){
		if(!containsTranslation(d)){
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values = new ContentValues();
	        values.put(KEY_PHRASE, d.getFirstAvailablePhrase() ); 
	        values.put(KEY_MEANING, d.getFirstAvailableMeaning() ); 
			
			db.insert(DATABASE_TABLE, null, values);
			
			db.close();
		}

	}
	
	private boolean containsTranslation(TranslationGroup group){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + 
									" WHERE " + KEY_PHRASE + "=?", 
									new String[]{ group.getFirstAvailablePhrase()});
		db.close();
		if(mCursor!=null){
			return true;
		}else{
			return false;
		}	
	}
	
	

	public TranslationGroup getTranslation(int id){
		 SQLiteDatabase db = this.getReadableDatabase();
		 
		 Cursor cursor = 
				 db.query(DATABASE_TABLE, 
						  COLUMNS, 
						  " id = ?", 
						  new String[] { String.valueOf(id) }, 
						  null, null, null);
		 
		 if (cursor != null){
			 cursor.moveToFirst();
		 }
		 // TODO realized the code and objects for all the translations are way too convoluted
		 TranslationGroup translation = new TranslationGroup();
		 translation.addPhrase(cursor.getString(1));
		 Translation t = new Translation();
		 t.addMeaning(new Meaning( cursor.getString(2), "") );
		 ArrayList<Translation> translationarray  = new ArrayList<Translation>();
		 translationarray.add(t);
		 
		 translation.addTranslation(translationarray );
		 
		 db.close();	 
		return translation;	 
	}
	/**
	 * Method for testing
	 */
	public void printDataBase(){
		List<TranslationGroup> trannys = this.getAllTranslations();
		for(TranslationGroup d : trannys){
			System.out.println(d.toString() );
		}
		
	}
	
	/**
	 * 
	 * returns a list of Transactions from the SQLite Database on the phone
	 * @return
	 */
	public List<TranslationGroup> getAllTranslations(){
		List<TranslationGroup> translations = new ArrayList<TranslationGroup>();
		
		String query = "SELECT * FROM " + DATABASE_TABLE;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		TranslationGroup data = null;
		if( cursor.moveToFirst()){
			do{
				data = new TranslationGroup();
								
				Translation t = new Translation();
				t.addPhrase(new Phrase( cursor.getString(1), "" ) );
				t.addMeaning(new Meaning(cursor.getString(2), "") );
					
				ArrayList<Translation> translationarray  = new ArrayList<Translation>();
				translationarray.add(t);
				data.addTranslation(translationarray);
				
				translations.add(data);
			}while(cursor.moveToNext());		
		}
		cursor.close();
		return translations;
	}
	
}
