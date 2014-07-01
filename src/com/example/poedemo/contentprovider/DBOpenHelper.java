package com.example.poedemo.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	private static SQLiteDatabase db ;
	
	
	private DBOpenHelper(Context context){
		super(context, MyProviderMetaData.DATABASE_NAME, null, MyProviderMetaData.DATABASE_VERSION);
	}
	
	public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {

		//create the table 
		db.execSQL("create table user(id int,name varchar(20))");  
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		//1.drop all database 
//		onCreate(arg0);
		db.execSQL("alter user t1 add age int"); 
	}

	public synchronized SQLiteDatabase getWritable(){
		synchronized (db) {
			
			if(db==null){
				db=  this.getWritableDatabase();
			}
		}
		
		return db;
	}
}
