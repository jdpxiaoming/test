package com.example.poedemo.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

//	private static SQLiteDatabase db ;
	
	public DBOpenHelper(Context context){
		super(context, MyProviderMetaData.DATABASE_NAME, null, MyProviderMetaData.DATABASE_VERSION);
	}
	
	public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//create the table 
		db.execSQL("create table users(_id int,name varchar(20),age int)");  
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
//		db.execSQL("alter users t1 add age int"); 
	}

//	public synchronized SQLiteDatabase getWritable(){
//		synchronized (this) {
//			
//			if(db==null){
//				db=  this.getWritableDatabase();
//			}
//		}
//		
//		return db;
//	}
}
