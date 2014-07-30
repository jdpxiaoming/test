package com.example.poedemo.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class MyViewPager extends ViewPager{
	private float xDistance, yDistance, xLast, yLast; 
	private boolean canScroll=true;
	
	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 是否支持手势滑动
	 * @param canscroll
	 */
	public void setScrollHorizolTounchable(boolean canscroll){
		this.canScroll	=	 canscroll;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		
//		if(!canScroll){
//			return false;
//		}else{
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
	             xLast = curX;  
	             yLast = curY;  
	             if(xDistance < yDistance){  
	                 return false;  
	             }   
	             
	             if(yDistance<100){
	            	 return true;
	             }
//		}
     }  
		 return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		
		switch (ev.getAction()) {  
        case MotionEvent.ACTION_DOWN:  
            xDistance = yDistance = 0f;  
            xLast = ev.getX();  
            yLast = ev.getY();  
            getParent().requestDisallowInterceptTouchEvent(true);  
            break;  
        case MotionEvent.ACTION_MOVE:  
            final float curX = ev.getX();  
            final float curY = ev.getY();             
            xDistance += Math.abs(curX - xLast);  
            yDistance += Math.abs(curY - yLast);  
            xLast = curX;  
            yLast = curY;  
            if(xDistance > yDistance){  
                getParent().requestDisallowInterceptTouchEvent(true);  
            }else{
            	getParent().requestDisallowInterceptTouchEvent(false);  
            }
            //是否禁止滑动
            if(!canScroll){
            	return true;
            }
        case MotionEvent.ACTION_CANCEL:
        	getParent().requestDisallowInterceptTouchEvent(false);  
        	break;
    }
		return super.dispatchTouchEvent(ev);
	}
	@Override
	public void postInvalidateDelayed(long delayMilliseconds, int left,
			int top, int right, int bottom) {
		// TODO Auto-generated method stub
		super.postInvalidateDelayed(delayMilliseconds, left, top, right, bottom);
	}
	
	
//	@Override
//	public boolean onTouchEvent(MotionEvent ev) {
//		// TODO Auto-generated method stub
////		int i=MotionEventCompat.ACTION_MASK;
////		return super.onTouchEvent(arg0);
//		
//		switch (ev.getAction()) {  
//        case MotionEvent.ACTION_DOWN:  
//            xDistance = yDistance = 0f;  
//            xLast = ev.getX();  
//            yLast = ev.getY();  
//            break;  
//        case MotionEvent.ACTION_MOVE:  
//            final float curX = ev.getX();  
//            final float curY = ev.getY();             
//            xDistance += Math.abs(curX - xLast);  
//            yDistance += Math.abs(curY - yLast);  
//            xLast = curX;  
//            yLast = curY;  
//            if(xDistance < yDistance){  
//                return false;  
//            }    
//    }  
//		 return super.onTouchEvent(ev);
//	}
	
}
