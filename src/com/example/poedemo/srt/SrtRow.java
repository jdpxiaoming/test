package com.example.poedemo.srt;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

/**
 * 字幕的一行数据
 * @author poe
 *
 */
public class SrtRow implements Comparable<SrtRow>{
	
	private static String TAG = "SrtRow";

	private long time;//计算距离开始时间的long值
	
	private String content ;//单词
	
	private String strTime	;

	public SrtRow(){};
	
	
	
	public SrtRow(long time, String content, String strTime) {
		super();
		this.time = time;
		this.content = content;
		this.strTime = strTime;
	}


	//[01:22.55] 害羞的红色 脸庞
	
	/**
	 * 	1
		00:00:01,990 --> 00:00:03,118
		Water
		
		2
		00:00:04,999 --> 00:00:07,197
		Earth
		
	 * @param standardLrcLine
	 * @return
	 */
	public static List<SrtRow> createRow(String standardLrcLine){
		
        try{
        	if(standardLrcLine.length()<10){
        		if(standardLrcLine.toCharArray()[0]>=48&&standardLrcLine.toCharArray()[0]<=57){
        			return null;
        		}
        	}
        	
            if(standardLrcLine.indexOf("[") != 0 || standardLrcLine.indexOf("]") != 13 ){
                return null;
            }
            int lastIndexOfRightBracket = standardLrcLine.lastIndexOf("]");
            String content = standardLrcLine.substring(lastIndexOfRightBracket + 1, standardLrcLine.length());
            
            // times [mm:ss.SS][mm:ss.SS] -> *mm:ss.SS**mm:ss.SS*
            String times = standardLrcLine.substring(0,lastIndexOfRightBracket + 1).replace("[", "-").replace("]", "-");
            String arrTimes[] = times.split("-");
            List<SrtRow> listTimes = new ArrayList<SrtRow>();
            for(String temp : arrTimes){
                if(temp.trim().length() == 0){
                    continue;
                }
                SrtRow lrcRow = new SrtRow( timeConvert(temp), content,temp);
                listTimes.add(lrcRow);
            }
            return listTimes;
        }catch(Exception e){
            Log.e(TAG,"createRows exception:" + e.getMessage());
            return null;
        }
    }
	
	 private static long timeConvert(String timeString){
	        timeString = timeString.replace(',', ':');
	        String[] times = timeString.split(":");
	        // mm:ss:SS
	        return Integer.valueOf(times[1]) * 60 * 1000 +
	                Integer.valueOf(times[2]) * 1000 +
	                Integer.valueOf(times[3]) ;
	    }
	 
	@Override
	public int compareTo(SrtRow another) {
		// TODO Auto-generated method stub
		return (int) (this.time-another.time);
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}
	
}
