package com.example.poedemo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.example.poedemo.MyApplication;

public class GenericUtil {

	/***
	 * 读取asset目录下歌词文件、 lrc
	 * @param fileName 歌词全程 **.lrc  **.srt 
	 * @return
	 */
	public static String getFromAssets(String fileName){ 
        try { 
            InputStreamReader inputReader = new InputStreamReader( MyApplication.App.getResources().getAssets().open(fileName) ); 
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null){
            	if(line.trim().equals(""))
            		continue;
            	Result += line + "\r\n";
            }
            return Result;
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return "";
    } 
	
	
	private static boolean isNumber(String line) {
		return false;
		// TODO Auto-generated method stub
		
	}


	/**
	 * 读取srt文件
	 * @param fileName
	 * @return
	 */
	public static String getSrtFromAssets(String fileName){ 
		String no ="";
		String time="";
		String content = "";
		
		try { 
			InputStreamReader inputReader = new InputStreamReader( MyApplication.App.getResources().getAssets().open(fileName) ); 
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line="";
			String Result="";
			while((line = bufReader.readLine()) != null){
				if(line.trim().equals(""))
					continue;
				if(isNumber(line)){
            		no	=	line	;
            	}else if(line.contains(":")){
					time	=	"["+line.substring(0, 12) +"]";
				}else{
					content	= line;
					Result += time+content + "\r\n";
				}
			}
			return Result;
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return "";
	} 
	
}
