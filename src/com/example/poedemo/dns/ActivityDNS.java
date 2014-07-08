package com.example.poedemo.dns;

import java.net.UnknownHostException;

import com.example.poedemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityDNS extends Activity {

	private EditText editText;
	private Button btn;
	private TextView text;
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.obj!=null){
				String ip = (String) msg.obj;
				 text.setText(ip);
			}
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_dns);
		
		editText		=	(EditText) findViewById(R.id.editText1);
		btn				=	(Button) findViewById(R.id.button1);
		text			=	(TextView) findViewById(R.id.textView1);
		
		
		editText.setText("www.baidu.com");
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						java.net.InetAddress x;
		                try {
		                        x = java.net.InetAddress.getByName("www.baidu.com");
		                        String ip = x.getHostAddress();// 得到字符串形式的ip地址
		                        System.out.println(ip);
		                       mHandler.sendMessage(mHandler.obtainMessage(0, ip));
		                } catch (UnknownHostException e) {
		                        // TODO Auto-generated catch block
		                        e.printStackTrace();
		                        
		                }
					}
				}).start();
				
			}
		});
	}
}
