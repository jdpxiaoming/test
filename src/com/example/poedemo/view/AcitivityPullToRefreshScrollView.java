package com.example.poedemo.view;

import java.util.ArrayList;
import java.util.List;
import com.example.poedemo.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class AcitivityPullToRefreshScrollView extends Activity {

//	private PullToRefresh
	private MyViewPager	viewpager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_scroll);
		
		viewpager		=	(MyViewPager) findViewById(R.id.viewpager1);
		
			LayoutInflater lin = LayoutInflater.from(this);
			List<View> views = new ArrayList<View>();
			for(int i=0;i<3;i++){
				View tab_home = lin.inflate(R.layout.item_home, null);
				ImageView img =(ImageView) tab_home.findViewById(R.id.imgOfHomeItem);
				img.setImageResource(R.drawable.tiger);
				views.add(tab_home);
			}
			
			setPageAdapter(views, viewpager);
	}

	public void setPageAdapter(final List<View> views,ViewPager view) {
		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			// @Override
			// public CharSequence getPageTitle(int position) {
			// return titles.get(position);
			// }

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		view.setAdapter(mPagerAdapter);
	}
}
