package com.example.poedemo.tabhost;

import java.util.ArrayList;
import java.util.List;
import com.example.poedemo.MainActivity;
import com.example.poedemo.R;
import com.example.poedemo.dns.ActivityDNS;
import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class TabHostActivity extends ActivityGroup {

	private TabHost mTabHost;
	private TabWidget mTabWidget;
	private List<TextView> tvs =new ArrayList<TextView>();
	private List<ImageView> imgs =new ArrayList<ImageView>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_tabhost);
		init();
	}

	private void init() {

		mTabHost		=	(TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this.getLocalActivityManager());
		
		mTabWidget		=	(TabWidget) findViewById(android.R.id.tabs);
		
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				int index = 0;
				if("首页".equals(tabId)){
					index	=	0;
					imgs.get(0).setImageResource(R.drawable.tab_home_press);
					if(imgs.size()>1){
						imgs.get(1).setImageResource(R.drawable.tab_queue_normal);
						imgs.get(2).setImageResource(R.drawable.tab_dynamic_normal);
					}
				}else if("排队".equals(tabId)){
					index	=	1;
					imgs.get(0).setImageResource(R.drawable.tab_home_normal);
					imgs.get(1).setImageResource(R.drawable.tab_queue_press);
					imgs.get(2).setImageResource(R.drawable.tab_dynamic_normal);
				}else {
					index	=	2;
					imgs.get(0).setImageResource(R.drawable.tab_home_normal);
					imgs.get(1).setImageResource(R.drawable.tab_queue_normal);
					imgs.get(2).setImageResource(R.drawable.tab_dynamic_press);
				}
				
				for(int i=0;i<tvs.size();i++){
					
					if(i==index){
						tvs.get(i).setTextColor(Color.RED);
					}else{
						tvs.get(i).setTextColor(Color.BLACK);
					}
				}
				
			}
		});
		
		/*String[] name = new String[]{"tab1","tab2","tab3"};
		for (int i = 0; i < name.length; i++) {// 复用布局，创建四个View的对象，并且分别设置View对象里的控件值和图片
			
			TabSpec spec = mTabHost.newTabSpec("tab" + i).setIndicator(name[i]);
			createTabLeaf(spec);
			
		}*/
		
		 Intent intent1 = new Intent(this,MainActivity.class);
		  createTab("首页",R.drawable.tab_home_normal,intent1);
		        
		  Intent intent2 = new Intent(this, ActivityDNS.class);
		  createTab("排队",R.drawable.tab_queue_normal,intent2);
		        
		  Intent intent3= new Intent(this, MainActivity.class);
		  createTab("我的",R.drawable.tab_dynamic_normal,intent3);
		        
//		  mTabHost.setCurrentTab(1);
	}
	
	private void createTab(String text ,int resImg,Intent intent){    
		
		mTabHost.addTab(mTabHost.newTabSpec(text).
				setIndicator(createTabView(text,resImg)).
				setContent(intent));
		
    }
	
	 private View createTabView(String text,int id) {
		 
         View view = LayoutInflater.from(this).inflate(R.layout.tab_indicator, null);
         TextView tv = (TextView) view.findViewById(R.id.tvOfItem);
         tv.setText(text);
         
         ImageView	img	=	(ImageView) view.findViewById(R.id.imgOfItem);
         img.setImageResource(id);
         
         imgs.add(img);
         tvs.add(tv);
         
         return view;
	 }
	 
	 
}
