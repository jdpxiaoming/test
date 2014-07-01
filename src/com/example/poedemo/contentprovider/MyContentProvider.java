package com.example.poedemo.contentprovider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

	/**
	 * 定义规则
	 */
	public static UriMatcher uriMatcher;
	public static int USERS_COLLECTION = 1; // 用于标记
	public static int USERS_SINGLE = 2; //
	private static DBOpenHelper databaseHelper;// sqlite包裹sqlite

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);// 试用一个没有规则的Uri。然后下面自己匹配。
		uriMatcher.addURI(MyProviderMetaData.AUTHORIY, "/users", USERS_COLLECTION);// 自己定义的规则，有点像路由器，是uri匹配的方案。
		uriMatcher.addURI(MyProviderMetaData.AUTHORIY, "/users/#", USERS_SINGLE);// 同上。
	}
	/**
	 * 为列定义别名
	 */
	public static HashMap<String, String> usersMap;
	static {
		usersMap = new HashMap<String, String>();
		usersMap.put(MyProviderMetaData.UserTableMetaData._ID, MyProviderMetaData.UserTableMetaData._ID);
		usersMap.put(MyProviderMetaData.UserTableMetaData.USER_NAME, MyProviderMetaData.UserTableMetaData.USER_NAME);
		usersMap.put(MyProviderMetaData.UserTableMetaData.USER_AGE, MyProviderMetaData.UserTableMetaData.USER_AGE);
	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
