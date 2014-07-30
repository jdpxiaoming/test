package com.example.poedemo.wlanprint;

import btmanager.Pos;

import com.example.poedemo.R;
import com.example.poedemo.util.ToastUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PathEffect;
import android.graphics.Typeface;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class ActivityCanvasTest extends Activity implements OnTouchListener {

	Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		ImageView view = new ImageView(this);
		view.setImageResource(R.drawable.tiger);
		setContentView(view);

		init(view);
		// testCanves(view);
	}


	/**
	 * 其中有很多埪 埪点1》bitmap.erasecolor 一般情况有用，但是 透明 和 black返回的 字节码 都是 0 所以 会被打印机误判为
	 * 黑色， 解决办法，直接全部刷成白底 ,实在不行 就 把bitmap的像素点抠出来一个一个设置颜色setPixel,getPixel
	 * 
	 * @param view
	 */
	private void init(ImageView view) {
		
		bitmap = Bitmap.createBitmap(384, 750, Config.ARGB_8888);
		bitmap.eraseColor(Color.WHITE);

		Canvas mCanvas = new Canvas(bitmap);
		Paint mPaint = new Paint();
//		mPaint.setTextAlign(Align.CENTER);
		mPaint.setTextSize(30);
		mPaint.setColor(Color.BLACK);

		//多行文字，如需换行采用下面的方法
		/*String aboutTheGame = "江村寨子xxxx店排号单";
		int tox =10,toy=20;
		float textsize=(float) 30.0;
		
		writerText(mCanvas,tox,toy,textsize,aboutTheGame);*/
		// 二维码
//		Bitmap viewBg = BitmapFactory.decodeResource(getResources(), R.drawable.bar2);
//		Matrix matrix = new Matrix();
//		int width = viewBg.getWidth();// 获取资源位图的宽	
//		int height = viewBg.getHeight();// 获取资源位图的高
//		float w = (float) 200 / viewBg.getWidth();
//		float h = (float) 200 / viewBg.getHeight();
//		matrix.postScale(w, h);// 获取缩放比例
//		Bitmap tiger = Bitmap.createBitmap(viewBg, 0, 0, width, height, matrix, true);
//		mCanvas.drawBitmap(tiger, (384-200)/2, 220, mPaint);

		mPaint.setTextSize(35);
		mCanvas.drawText("江村寨子xxxx店排号单", 10, 30, mPaint);
		
		//虚线
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.STROKE);  
		PathEffect effects = new DashPathEffect(new float[]{5,5,5,5},1);  
		mPaint.setPathEffect(effects);
		mCanvas.drawLine(0, 60, 384, 60, mPaint);
		
		//3.餐厅名字,桌型及 排单号码
//		mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
		mPaint.setStyle(Paint.Style.FILL);  
		mPaint.setTextSize(60);
		mCanvas.drawText("A003",384/2-60, 110, mPaint);
		
		mPaint.setTextSize(40);
		mCanvas.drawText("桌型：小桌(3-4人)", 10, 160, mPaint);
		mCanvas.drawText("人数：3位", 10, 210, mPaint);
		
		
		mPaint.setTextSize(25);
		mPaint.setTextAlign(Align.LEFT);
		mCanvas.drawText("您前面还有 2 桌在等候!", 15, 250, mPaint);
		//虚线
				mPaint.setAntiAlias(true);
				mPaint.setStyle(Paint.Style.STROKE);  
				mCanvas.drawLine(0, 255, 384, 255, mPaint);
				
		// 5.等待人数/电话等信息
		mPaint.setStyle(Paint.Style.FILL); 
//		mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
		mPaint.setTextSize(20);
		mCanvas.drawText("*打印时间：2014-7-21 18:00:00", 10, 280, mPaint);
		mCanvas.drawText("*请注意迎宾叫号，过号请到迎宾台 ", 10,305, mPaint);
		mCanvas.drawText("*免责声明：短信提醒可能延迟 ", 10,330, mPaint);
		mCanvas.drawText("*排队神器“妙队”下载地址 ", 10,355, mPaint);
		mCanvas.drawText("http://app.ieth.cn ", 15,380, mPaint);

		if (bitmap != null) {
			view.setImageBitmap(bitmap);
			 Pos.POS_PrintPicture(bitmap, 384, 0);
			ToastUtil.throwTipShort("打印中...");
		} else {
			ToastUtil.throwTipShort("获取的人造view为空");
		}

	}

	private void writerText(Canvas mCanvas, int tox, int toy, float textsize, String aboutTheGame) {
		// TODO Auto-generated method stub
		TextPaint textPaint = new TextPaint();
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(textsize);
		StaticLayout layout = new StaticLayout(aboutTheGame, textPaint, 384, Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
		mCanvas.translate(tox, toy);
		layout.draw(mCanvas);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
