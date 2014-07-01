package com.example.poedemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter4Main extends BaseAdapter {

	private List<String> datas;
	private LayoutInflater lin;
	
	
	
	public Adapter4Main(List<String> datas,Context context) {
		super();
		lin	=	LayoutInflater.from(context);
		this.datas = datas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return datas.indexOf(datas.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null||convertView.findViewById(R.id.textOfListviewItem)==null){
			convertView	=	lin.inflate(R.layout.item_listview, null);
			convertView.setTag(R.id.textOfListviewItem, convertView.findViewById(R.id.textOfListviewItem));
		}
		
		TextView tv = (TextView) convertView.getTag(R.id.textOfListviewItem);
		tv.setText(datas.get(position));
		
		return convertView;
	}

}
