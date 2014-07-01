package com.example.poedemo.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.example.poedemo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SortActivity extends Activity {

	private TextView	 text1,textSort,text2;
	List<food> dataList =new ArrayList<food>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_sort);
		
		init();
		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		text1		=	(TextView) findViewById(R.id.text1OfSort);
		text2		=	(TextView) findViewById(R.id.text2OfSort);
		
		textSort	=	(TextView) findViewById(R.id.textSortOfSort);
		textSort.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				
				System.out.println("sort前...");
				print(dataList);
				
				Collections.sort(dataList);
				
				System.out.println("sort后...");
				print(dataList);
				
				
				StringBuffer sb = new StringBuffer();
				for(food f:dataList){
					sb.append(f.getName()+",");
				}
			
				text2.setText("排序后：==》》\n"+sb.toString());
			}
		});
		
		
		
		addData("红酒", "酒水", dataList);
		addData("白酒", "酒水", dataList);
		addData("猪头肉", "冷菜", dataList);
		addData("二锅头", "酒水", dataList);
		addData("茼蒿", "冷菜", dataList);
		addData("东北白米饭", "主食", dataList);
		addData("日本豆腐", "冷菜", dataList);
		
		StringBuffer sb = new StringBuffer();
		for(food f:dataList){
			sb.append(f.getName()+",");
		}
	
		text1.setText("排序前：==》》\n"+sb.toString());
	}
	
	private void print(List<food> dataList){
		for(int i=0;i<dataList.size();i++){
			System.out.println(dataList.get(i).getName());
		}
	}
	
	private void addData(String name,String type,List<food> lists){
		food  f= new food();
		f.setName(name);
		f.setType(type);
		lists.add(f);
	}
}
