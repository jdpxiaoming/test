package com.example.poedemo.lrc;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.poedemo.util.GenericUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class ActivityLRC extends Activity{

	public final static String TAG = "ActivitySRT";
	ILrcView mLrcView;
    private int mPalyTimerDuration = 1000;
    private Timer mTimer;
    private TimerTask mTask;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLrcView = new LrcView(this, null);
        setContentView((View) mLrcView);
        //file:///android_asset/test.lrc;
        String lrc = GenericUtil.getFromAssets("test.lrc");
        Log.d(TAG, "lrc:" + lrc);
        
        ILrcBuilder builder = new DefaultLrcBuilder();
        List<LrcRow> rows = builder.getLrcRows(lrc);
        
        mLrcView.setLrc(rows);
        beginLrcPlay();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onDestroy() {
    	
    	if(mTimer!=null){mTimer.cancel();mTimer=null;}
    	if(mTask!=null){mTask.cancel();mTask = null;}
    	
    	super.onDestroy();
    }
    
      
    // emulate music play
    public void beginLrcPlay(){
        if(mTimer == null){
            mTimer = new Timer();
            mTask = new LrcTask();
            mTimer.scheduleAtFixedRate(mTask, 0, mPalyTimerDuration);
        }
    }
    
    public void stopLrcPlay(){
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }
    
    class LrcTask extends TimerTask{
        
        long beginTime = -1;
        
        @Override
        public void run() {
            if(beginTime == -1) {
                beginTime = System.currentTimeMillis();
            }
            
            final long timePassed = System.currentTimeMillis() - beginTime;
            ActivityLRC.this.runOnUiThread(new Runnable() {
                
                public void run() {
                    mLrcView.seekLrcToTime(timePassed);
                }
            });
           
        }
    };
}
