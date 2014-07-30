package com.example.poedemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class MyPullTorefreshView extends PullToRefreshScrollView {

	public MyPullTorefreshView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyPullTorefreshView(Context context, com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode, com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle style) {
		super(context, mode, style);
		// TODO Auto-generated constructor stub
	}

	public MyPullTorefreshView(Context context, com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode) {
		super(context, mode);
		// TODO Auto-generated constructor stub
	}

	public MyPullTorefreshView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private float xDistance, yDistance, xLast, yLast; 
	
	@Override
	public boolean onGenericMotionEvent(MotionEvent ev) {
		
		 switch (ev.getAction()) {  
         case MotionEvent.ACTION_DOWN:  
             xDistance = yDistance = 0f;  
             xLast = ev.getX();  
             yLast = ev.getY();  
             break;  
         case MotionEvent.ACTION_MOVE:  
             final float curX = ev.getX();  
             final float curY = ev.getY();             
             xDistance += Math.abs(curX - xLast);  
             yDistance += Math.abs(curY - yLast);  
             
             System.out.println("x:"+xDistance+"y:"+yDistance);
             xLast = curX;  
             yLast = curY;  
             if(xDistance < yDistance){  
                 return false;  
             }   
		 }
		return super.onGenericMotionEvent(ev);
	}
	
	@Override
	public boolean onInterceptHoverEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
