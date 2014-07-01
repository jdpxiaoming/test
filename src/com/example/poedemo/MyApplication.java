package com.example.poedemo;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class MyApplication extends Application {

	public static MyApplication App;
	public static SharedPreferences sp = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		App = this;
		sp = PreferenceManager.getDefaultSharedPreferences(this);
	}
	
	/**
	 * 存放token的方法
	 * 
	 * @param tag
	 * @param data
	 */
	public static void pushPreferenceData(String tag, String data) {
		Editor editor = sp.edit();
		editor.putString(tag, data);
		editor.commit();
	}
	
	/**
	 * 存放token的方法
	 * 
	 * @param tag
	 * @param data
	 */
	public static void pushPreferenceData(String tag, boolean data) {
		Editor editor = sp.edit();
		editor.putBoolean(tag, data);
		editor.commit();
	}

	/**
	 * 取出参数token
	 * 
	 * @param tag
	 *            标记
	 * @return
	 */
	public static String getPreferenceString(String tag) {

		return sp.getString(tag, null);
	}
	
	/**
	 * 取出参数token
	 * 
	 * @param tag
	 *            标记
	 * @return
	 */
	public static boolean getPreferenceBooL(String tag) {
		
		return sp.getBoolean(tag, false);
	}
}
