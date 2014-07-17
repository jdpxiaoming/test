package com.example.poedemo.wlanprint;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import com.example.poedemo.R;
import com.example.poedemo.util.ToastUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebView.PictureListener;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/**
 * html invisable 基本无法提供 bitmpa
 * 此方案 废除 与2014-7-17 12:30 
 * 新方案： canvas自己 绘制 bitmap
 * @author poe
 *
 */
public class ActivityHtmlLoadTest extends Activity {

	private String baseURL="file:///android_asset/template.html";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		WebView webview = new WebView(this);
		ImageView image =new ImageView(this);
		image.setImageResource(R.drawable.tiger);
		setContentView(image);
		
		/*Bitmap bmap = getBitmap(webview, 500, 500, baseURL, "打印机测试");
		
		if(null!=bmap){
			ToastUtil.throwTipShort("bitmap不为空！");
			image.setImageBitmap(bmap);
		}else{
			ToastUtil.throwTipShort("bitmap为空！");
		}*/
		
//		webview.loadUrl(baseURL);
	}
	
	public Bitmap getBitmap(final WebView w, int containerWidth, int containerHeight, final String baseURL, final String content) {
	    final CountDownLatch signal = new CountDownLatch(1);
	    final Bitmap b = Bitmap.createBitmap(containerWidth, containerHeight, Bitmap.Config.ARGB_8888);
	    final AtomicBoolean ready = new AtomicBoolean(false); 
	    w.post(new Runnable() {

	        @Override
	        public void run() {
	            w.setWebViewClient(new WebViewClient() {
	                @Override
	                public void onPageFinished(WebView view, String url) {
	                    ready.set(true);
	                }
	            });
	            w.setPictureListener(new PictureListener() {
					@Override
					public void onNewPicture(WebView view, Picture picture) {
						// TODO Auto-generated method stub
						 if (ready.get()) {
		                        final Canvas c = new Canvas(b);
		                        view.draw(c);
		                        w.setPictureListener(null);
		                        signal.countDown();
		                    }
					}
	            });
	            w.layout(0, 0, 500,500);
	            w.loadDataWithBaseURL(baseURL, content, "text/html", "UTF-8", null);
	        }});
	    try {
	        signal.await();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    return b;
	}
}
