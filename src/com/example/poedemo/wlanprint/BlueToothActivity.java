package com.example.poedemo.wlanprint;

import com.example.poedemo.MainActivity;
import com.example.poedemo.R;

import btmanager.LayoutUtils;
import btmanager.Pos;
import btmanager.ReadThread;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class BlueToothActivity extends Activity implements OnClickListener {

	private BroadcastReceiver broadcastReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		if (BluetoothAdapter.getDefaultAdapter() == null) {
			Toast.makeText(this, getString(R.string.bluetoothrequired), Toast.LENGTH_SHORT).show();
			finish();
			return;
		}

		if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
			BluetoothAdapter.getDefaultAdapter().enable();
		}

		/*
		 * Button btn = new Button(this); btn.setText("打印");
		 * 
		 * setContentView(btn);
		 * 
		 * btn.setOnClickListener(this);
		 */
		LayoutUtils.initContentView(this, R.layout.actionbar, R.layout.linearlayoutsetting);

		init();
		initBroadcast();

		if (!BtService.isRunning()) {
			Intent intent = new Intent(this, BtService.class);
			startService(intent);
		}

	}

	private void init() {
		// TODO Auto-generated method stub
		Button btSetting01 = (Button) findViewById(R.id.btSetting01);
		Button btSetting02 = (Button) findViewById(R.id.btSetting02);
		Button btSetting03 = (Button) findViewById(R.id.btSetting03);
		Button btSetting04 = (Button) findViewById(R.id.btSetting04);
		Button btSetting05 = (Button) findViewById(R.id.btSetting05);
		Button btSetting06 = (Button) findViewById(R.id.btSetting06);
		Button btSetting07 = (Button) findViewById(R.id.btSetting07);
		Button btSetting08 = (Button) findViewById(R.id.btSetting08);
		btSetting01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(BlueToothActivity.this, Connect.class);
				startActivity(intent);
			}

		});
		btSetting02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(BlueToothActivity.this, Qrcode.class);
				startActivity(intent);
			}

		});

		btSetting03.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(BlueToothActivity.this, Configue.class);
//				startActivity(intent);
				
				/* String tmp ="test by poe!";
				 Pos.POS_S_TextOut(tmp, 0, SetAndShow.nScaleTimesWidth,
				 SetAndShow.nScaleTimesHeight, SetAndShow.nFontSize,
				 SetAndShow.nFontStyle);
				 Pos.POS_FeedLine();*/
				
				 WebView webView = new WebView(BlueToothActivity.this);
				 WebSettings webSettings = webView.getSettings();
			        webSettings.setLoadWithOverviewMode(true);
			        webSettings.setUseWideViewPort(true);
				webView.loadUrl("file:///android_asset/template.html");
				
//				if (webView.getVisibility() == View.VISIBLE) {
//					Bitmap mBitmap = captureWebView(webView);
				Bitmap mBitmap	= BitmapFactory.decodeResource(getResources(), R.drawable.tiger);
					Pos.POS_PrintPicture(mBitmap, 384, 0);
//				}
				
			}

		});

		/*
		 * btSetting04.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Intent intent = new Intent(MainActivity.this,
		 * UpdateProgram.class); startActivity(intent); }
		 * 
		 * }); btSetting05.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub
		 * 
		 * }
		 * 
		 * }); btSetting06.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Intent intent = new Intent(MainActivity.this,
		 * AutoConnect.class); startActivity(intent); }
		 * 
		 * }); btSetting07.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Intent intent = new Intent(MainActivity.this, SetKey.class);
		 * startActivity(intent); }
		 * 
		 * }); btSetting08.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Intent intent = new Intent(MainActivity.this, Help.class);
		 * startActivity(intent); }
		 * 
		 * });
		 */
	}

	// 打印整页
		private Bitmap captureWebView(WebView webView) {
			Picture snapShot = webView.capturePicture();

			Bitmap bmp = Bitmap.createBitmap(snapShot.getWidth(),
					snapShot.getHeight(), Bitmap.Config.RGB_565);
			Canvas canvas = new Canvas(bmp);
			snapShot.draw(canvas);
			Pos.saveMyBitmap(bmp);
			return bmp;
		}
		
	private void initBroadcast() {
		broadcastReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String action = intent.getAction();
				if (action.equals(BtService.ACTION_SERVICEREADY)) {
					// 子线程handler准备完毕
					Pos.POS_Read();

				} else if (action.equals(OptionsActivity.FINISH)) {
					finish();
				}
			}
		};

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(BtService.ACTION_SERVICEREADY);
		intentFilter.addAction(OptionsActivity.FINISH);
		registerReceiver(broadcastReceiver, intentFilter);

	}

	private void uninitBroadcast() {
		unregisterReceiver(broadcastReceiver);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	private void readAllPreferences() {
		synchronized (MainActivity.lock_preferences) {
			// 这个异常是正常的，不影响实际，因为有默认值
			try {
				SharedPreferences mSharedPreferences = this.getSharedPreferences(MainActivity.PREFERENCES_FILE, 0);

				// AutoConnect
				AutoConnect.autoConnectName = mSharedPreferences.getString(AutoConnect.PREFERENCES_autoConnectName, "");
				AutoConnect.autoConnectMac = mSharedPreferences.getString(AutoConnect.PREFERENCES_autoConnectMac, "");
				AutoConnect.autoConnectMode = mSharedPreferences.getString(AutoConnect.PREFERENCES_autoConnectMode, AutoConnect.VALUE_autoConnectModeNotSet);

				// OptionsActivity
				OptionsActivity.setDebug(mSharedPreferences.getBoolean(OptionsActivity.PREFERENCES_debug, false));

				// Configue
				Configue.strsystemname = mSharedPreferences.getString(Configue.PREFERENCES_SystemName, getString(R.string.defaultprintername));
				Configue.strsystemsn = mSharedPreferences.getString(Configue.PREFERENCES_SystemSN, getString(R.string.defaultprintersn));
				Configue.strbtname = mSharedPreferences.getString(Configue.PREFERENCES_Btname, getString(R.string.defaultbtname));
				Configue.strbtpwd = mSharedPreferences.getString(Configue.PREFERENCES_Btpwd, getString(R.string.defaultbtpwd));
				Configue.nbaudrate = mSharedPreferences.getInt(Configue.PREFERENCES_Baudrate, 9600);
				Configue.nlanguage = mSharedPreferences.getInt(Configue.PREFERENCES_Language, 255);
				Configue.ndarkness = mSharedPreferences.getInt(Configue.PREFERENCES_Darkness, 0);
				Configue.ndefaultfont = mSharedPreferences.getInt(Configue.PREFERENCES_DefaultFont, 0);
				Configue.nlfcr = mSharedPreferences.getInt(Configue.PREFERENCES_LFCR, 0);
				Configue.nIdletime = mSharedPreferences.getInt(Configue.PREFERENCES_Idletime, 180);
				Configue.nPwofftime = mSharedPreferences.getInt(Configue.PREFERENCES_Powerofftime, 1800);
				Configue.nMaxfeedlength = mSharedPreferences.getInt(Configue.PREFERENCES_Maxfeedlength, 300);
				Configue.nBlackmarklength = mSharedPreferences.getInt(Configue.PREFERENCES_Blackmarklength, 300);

				// SetAndShow
				SetAndShow.nDarkness = mSharedPreferences.getInt(SetAndShow.PREFERENCES_TEXT_nDarkness, 0);
				SetAndShow.nFontSize = mSharedPreferences.getInt(SetAndShow.PREFERENCES_TEXT_nFontSize, 0);
				SetAndShow.nTextAlign = mSharedPreferences.getInt(SetAndShow.PREFERENCES_TEXT_nTextAlign, 0);
				SetAndShow.nScaleTimesWidth = mSharedPreferences.getInt(SetAndShow.PREFERENCES_TEXT_nScaleTimesWidth, 0);
				SetAndShow.nScaleTimesHeight = mSharedPreferences.getInt(SetAndShow.PREFERENCES_TEXT_nScaleTimesHeight, 0);
				SetAndShow.nFontStyle = mSharedPreferences.getInt(SetAndShow.PREFERENCES_TEXT_nFontStyle, 0);

				// UpdateProgramOptions
				UpdateProgramOptions.programPath = mSharedPreferences.getString(UpdateProgramOptions.PREFERENCES_ProgramPath, "");

				// Barcode
				Barcode.nBarcodetype = mSharedPreferences.getInt(Barcode.PREFERENCES_TEXT_Barcodetype, 0);
				Barcode.nStartOrgx = mSharedPreferences.getInt(Barcode.PREFERENCES_TEXT_StartOrgx, 0);
				Barcode.nBarcodeWidth = mSharedPreferences.getInt(Barcode.PREFERENCES_TEXT_BarcodeWidth, 0);
				Barcode.nBarcodeHeight = mSharedPreferences.getInt(Barcode.PREFERENCES_TEXT_BarcodeHeight, 0);
				Barcode.nBarcodeFontType = mSharedPreferences.getInt(Barcode.PREFERENCES_TEXT_BarcodeFontType, 0);
				Barcode.nBarcodeFontPosition = mSharedPreferences.getInt(Barcode.PREFERENCES_TEXT_BarcodeFontPosition, 0);

				// Qrcode
				Qrcode.nQrcodetype = mSharedPreferences.getInt(Qrcode.PREFERENCES_TEXT_Qrcodetype, 0);
				Qrcode.nQrcodeWidth = mSharedPreferences.getInt(Qrcode.PREFERENCES_TEXT_QrcodeWidth, 0);
				Qrcode.nErrorCorrectionLevel = mSharedPreferences.getInt(Qrcode.PREFERENCES_TEXT_ErrorCorrectionLevel, 0);

				// SetKey
				SetKey.deskey = mSharedPreferences.getString(SetKey.PREFERENCES_deskey, "12345678");
				ReadThread.setKey(SetKey.deskey.getBytes());

				// GuideActivity bookmarks
				GuideActivity.bookmarkswebsite[0] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark1website, GuideActivity.baiduwebsite);
				GuideActivity.bookmarkswebsite[1] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark2website, GuideActivity.baiduwebsite);
				GuideActivity.bookmarkswebsite[2] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark3website, GuideActivity.baiduwebsite);
				GuideActivity.bookmarkswebsite[3] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark4website, GuideActivity.baiduwebsite);
				GuideActivity.bookmarkswebsite[4] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark5website, GuideActivity.baiduwebsite);
				GuideActivity.bookmarkswebsite[5] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark6website, GuideActivity.baiduwebsite);
				GuideActivity.bookmarkswebsite[6] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark7website, GuideActivity.baiduwebsite);
				GuideActivity.bookmarkswebsite[7] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark8website, GuideActivity.baiduwebsite);
				GuideActivity.bookmarksname[0] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark1name, GuideActivity.baiduname);
				GuideActivity.bookmarksname[1] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark2name, GuideActivity.baiduname);
				GuideActivity.bookmarksname[2] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark3name, GuideActivity.baiduname);
				GuideActivity.bookmarksname[3] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark4name, GuideActivity.baiduname);
				GuideActivity.bookmarksname[4] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark5name, GuideActivity.baiduname);
				GuideActivity.bookmarksname[5] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark6name, GuideActivity.baiduname);
				GuideActivity.bookmarksname[6] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark7name, GuideActivity.baiduname);
				GuideActivity.bookmarksname[7] = mSharedPreferences.getString(GuideActivity.PREFERENCES_WEB_bookmark8name, GuideActivity.baiduname);

			} catch (Exception e) {
				if (OptionsActivity.getDebug())
					Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
			}
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		uninitBroadcast();
		super.onDestroy();
	}
}
