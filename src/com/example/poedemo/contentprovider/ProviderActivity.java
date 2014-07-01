package com.example.poedemo.contentprovider;

import com.example.poedemo.R;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ProviderActivity extends Activity {

	private TextView textAdd,textDesc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_provider);
		
		init();
	}

	private void init() {
		textAdd		=	(TextView) findViewById(R.id.textAdd);
		textDesc	=	(TextView) findViewById(R.id.text2);
		
		textAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addOneUser();
			}

		});
		
		getData();
	}
	
	private void addOneUser() {
		// TODO Auto-generated method stub
		ContentValues contentValues = new ContentValues();  
        contentValues.put("name","zhangsan");  
        contentValues.put("age",19);  
        Uri uri = getContentResolver().insert(Uri.parse("content://com.example.poedemo.MyContentProvider/users"),contentValues);  
        Log.i("juetion", "insert uri-->" + uri.toString());
        
        getData();
	}
	
	
	private void getData() {
		Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.poedemo.MyContentProvider/users"),  
                null,  
                null, null, null);  
		StringBuffer sb = new StringBuffer();
        while (cursor.moveToNext()) {  
            Log.i("juetion", cursor.getString(cursor.getColumnIndex("name"))); 
            sb.append(cursor.getString(cursor.getColumnIndex("name")));
        }  
        
        textDesc.setText(sb.toString());
	}
}
