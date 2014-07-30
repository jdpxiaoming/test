package com.example.poedemo;

import com.example.poedemo.contentprovider.ProviderActivity;
import com.example.poedemo.dns.ActivityDNS;
import com.example.poedemo.lrc.ActivityLRC;
import com.example.poedemo.sort.SortActivity;
import com.example.poedemo.srt.ActivitySRT;
import com.example.poedemo.util.ToastUtil;
import com.example.poedemo.view.AcitivityPullToRefreshScrollView;
import com.example.poedemo.wlanprint.BlueToothActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 以后我会把我写过的所有demo程序注意收集起来
 * 本程序入口采用listview来列出所有的demo情况
 * @author poe
 *
 */
public class MainActivity extends Activity {

	private ListView listview ;
	public static final Object lock_preferences = new Object();
	// Preferences field
	public static final String PREFERENCES_FILE = "com.example.poedemo.preferencesfile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);
		
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		listview	=	(ListView) findViewById(R.id.listviewOfMain);
		
		
		
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
				if(arg2==0){
					ToastUtil.throwTipLong("加载lrc歌词文件");
					startActivity(new Intent(MainActivity.this,ActivityLRC.class));
				}
				
				if(arg2==1){
					ToastUtil.throwTipLong("加载srt视频字幕文件");
					startActivity(new Intent(MainActivity.this,ActivitySRT.class));
				}
				
				if(arg2==2){
					startActivity(new Intent(MainActivity.this,SortActivity.class));
				}
				
				if(arg2==3){
					startActivity(new Intent(MainActivity.this,ProviderActivity.class));
				}
				
				if(arg2==4){//dns
					startActivity(new Intent(MainActivity.this,ActivityDNS.class));
				}
				
				if(arg2==5){//blue tooth print
					
					startActivity(new Intent(MainActivity.this,BlueToothActivity.class));
				}
				if(arg2==7){//blue tooth print
					
					startActivity(new Intent(MainActivity.this,AcitivityPullToRefreshScrollView.class));
				}
			}
			
		});
	
	String[] datas = getResources().getStringArray(R.array.demolist);
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_listview,R.id.textOfListviewItem, datas);
	listview.setAdapter(adapter);
	}
	
}
