package translation;

import java.util.ArrayList;
import java.util.LinkedList;
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
	private static final int DATABASE_VERSION = 3;
	
	
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
		// TODO Auto-generated method stub
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
	
	public void addTranslation(TranslationData d){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
        values.put(KEY_PHRASE, d.getFirstAvailablePhrase() ); 
        values.put(KEY_MEANING, d.getFirstAvailableMeaning() ); 
		
		db.insert(DATABASE_TABLE, null, values);
		
		db.close();
	}

	public TranslationData getTranslation(int id){
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
		 TranslationData translation = new TranslationData();
		 translation.addPhrase(cursor.getString(1));
		 Translation t = new Translation();
		 t.addMeaning(new Meaning( cursor.getString(2), "") );
		 ArrayList<Translation> translationarray  = new ArrayList<Translation>();
		 translationarray.add(t);
		 
		 translation.addTranslation(translationarray );
		 
		 db.close();	 
		return translation;	 
	}
	
	public void printDataBase(){
		List<TranslationData> trannys = this.getAllTranslations();
		for(TranslationData d : trannys){
			
			System.out.println(d.toString() );
		}
		
	}
	
	public List<TranslationData> getAllTranslations(){
		List<TranslationData> translations = new ArrayList<TranslationData>();
		
		String query = "SELECT * FROM " + DATABASE_TABLE;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		TranslationData data = null;
		if( cursor.moveToFirst()){
			do{
				data = new TranslationData();
				
				
				Translation t = new Translation();
				t.addPhrase(new Phrase( cursor.getString(1), "" ) );
			//	String mean = cursor.getString(2);
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
