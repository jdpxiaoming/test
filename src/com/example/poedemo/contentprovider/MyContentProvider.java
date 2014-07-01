package com.example.poedemo.contentprovider;

import java.util.HashMap;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class MyContentProvider extends ContentProvider {

	/**
	 * 定义规则
	 */
	public static UriMatcher uriMatcher;
	public static final int USERS_COLLECTION = 1; // 用于标记
	public static final int USERS_SINGLE = 2; //
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

    public boolean onCreate() {  
        Log.i("juetion","onCreate");  
        databaseHelper = new DBOpenHelper(getContext());//这里的实现，常见前篇关于Sqlite的文章。  
        return true;  
    }  
  
    @Override  
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {  
        Log.i("juetion","query");  
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();//写入查询条件，有点像Hibernate。  
        switch (uriMatcher.match(uri)) {//判断查询的是单个数据还是多个数据。  
            case USERS_SINGLE:  
                sqLiteQueryBuilder.setTables(MyProviderMetaData.UserTableMetaData.TABLE_NAME);//需要查询的表  
                sqLiteQueryBuilder.setProjectionMap(usersMap);//列的别名定义  
                sqLiteQueryBuilder.appendWhere(MyProviderMetaData.UserTableMetaData._ID + "=" + uri.getPathSegments().get(1));  
                //查询条件，uri.getPathSegments().get(1)，getPathSegments是将内容根据／划分成list。  
                break;  
            case USERS_COLLECTION:  
                sqLiteQueryBuilder.setTables(MyProviderMetaData.UserTableMetaData.TABLE_NAME);  
                sqLiteQueryBuilder.setProjectionMap(usersMap);  
                break;  
        }  
        String orderBy;//判断sortOrder是否为空，加入默认。  
        if (TextUtils.isEmpty(sortOrder)) {  
            orderBy = MyProviderMetaData.UserTableMetaData.DEFAULT_SORT_ORDER;  
        } else {  
            orderBy = sortOrder;  
        }  
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();  
//        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);//可以使用下面的方法，不过此时sqLiteDatabase将会没有用。  
        Cursor cursor = sqLiteDatabase.query(MyProviderMetaData.UserTableMetaData.TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);  
        cursor.setNotificationUri(getContext().getContentResolver(),uri);  
        return cursor;  
    }  
  
    /** 
     * 根据传入的URI，返回URI说表示的数据类型 
     * @param uri 
     * @return 
     */  
    @Override  
    public String getType(Uri uri) {  
        Log.i("juetion","getType");  
        switch (uriMatcher.match(uri)) {//匹配uri的规则  
            case USERS_COLLECTION:  
                return MyProviderMetaData.UserTableMetaData.CONTENT_TYPE;  
            case USERS_SINGLE:  
                return MyProviderMetaData.UserTableMetaData.CONTENT_TYPE_ITEM;  
            default:  
                throw new IllegalArgumentException("Unknown URI" + uri);  
        }  
    }  
  
    @Override  
    public Uri insert(Uri uri, ContentValues values) {  
        Log.i("juetion","insert");  
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();  
        long rowId = sqLiteDatabase.insert(MyProviderMetaData.UserTableMetaData.TABLE_NAME, null, values);  
        if (rowId > 0) {  
            Uri insertUserUri = ContentUris.withAppendedId(MyProviderMetaData.UserTableMetaData.CONTENT_URI, rowId);//简单来说就是字符串拼凑一下。只不过是uri专用的。  
            //通知监听器  
            getContext().getContentResolver().notifyChange(insertUserUri,null);  
            return insertUserUri;  
        }else  
            throw new IllegalArgumentException("Failed to insert row into" + uri);  
    }  
  
    @Override  
    public int delete(Uri uri, String selection, String[] selectionArgs) {  
        Log.i("juetion","delete");  
        return 0;  
    }  
  
    @Override  
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {  
        Log.i("juetion","update");  
        return 0;  
    }  
}
