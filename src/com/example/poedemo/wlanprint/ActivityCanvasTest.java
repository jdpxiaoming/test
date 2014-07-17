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
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
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

	private void testCanves(ImageView view) {

		bitmap = Bitmap.createBitmap(384, 800, Config.ARGB_8888);
		bitmap.eraseColor(Color.WHITE);

		Canvas canvas = new Canvas(bitmap);

		// 创建画笔
		Paint p = new Paint();
		p.setColor(Color.RED);// 设置红色

		canvas.drawText("画圆：", 10, 20, p);// 画文本
		canvas.drawCircle(60, 20, 10, p);// 小圆
		p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
		canvas.drawCircle(120, 20, 20, p);// 大圆

		canvas.drawText("画线及弧线：", 10, 60, p);
		p.setColor(Color.GREEN);// 设置绿色
		canvas.drawLine(60, 40, 100, 40, p);// 画线
		canvas.drawLine(110, 40, 190, 80, p);// 斜线
		// 画笑脸弧线
		p.setStyle(Paint.Style.STROKE);// 设置空心
		RectF oval1 = new RectF(150, 20, 180, 40);
		canvas.drawArc(oval1, 180, 180, false, p);// 小弧形
		oval1.set(190, 20, 220, 40);
		canvas.drawArc(oval1, 180, 180, false, p);// 小弧形
		oval1.set(160, 30, 210, 60);
		canvas.drawArc(oval1, 0, 180, false, p);// 小弧形

		canvas.drawText("画矩形：", 10, 80, p);
		p.setColor(Color.GRAY);// 设置灰色
		p.setStyle(Paint.Style.FILL);// 设置填满
		canvas.drawRect(60, 60, 80, 80, p);// 正方形
		canvas.drawRect(60, 90, 160, 100, p);// 长方形

		canvas.drawText("画扇形和椭圆:", 10, 120, p);
		/* 设置渐变色 这个正方形的颜色是改变的 */
		Shader mShader = new LinearGradient(0, 0, 100, 100, new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.LTGRAY }, null, Shader.TileMode.REPEAT); // 一个材质,打造出一个线性梯度沿著一条线。
		p.setShader(mShader);
		// p.setColor(Color.BLUE);
		RectF oval2 = new RectF(60, 100, 200, 240);// 设置个新的长方形，扫描测量
		canvas.drawArc(oval2, 200, 130, true, p);
		// 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
		// 画椭圆，把oval改一下
		oval2.set(210, 100, 250, 130);
		canvas.drawOval(oval2, p);

		canvas.drawText("画三角形：", 10, 200, p);
		// 绘制这个三角形,你可以绘制任意多边形
		Path path = new Path();
		path.moveTo(80, 200);// 此点为多边形的起点
		path.lineTo(120, 250);
		path.lineTo(80, 250);
		path.close(); // 使这些点构成封闭的多边形
		canvas.drawPath(path, p);

		// 你可以绘制很多任意多边形，比如下面画六连形
		p.reset();// 重置
		p.setColor(Color.LTGRAY);
		p.setStyle(Paint.Style.STROKE);// 设置空心
		Path path1 = new Path();
		path1.moveTo(180, 200);
		path1.lineTo(200, 200);
		path1.lineTo(210, 210);
		path1.lineTo(200, 220);
		path1.lineTo(180, 220);
		path1.lineTo(170, 210);
		path1.close();// 封闭
		canvas.drawPath(path1, p);
		/*
		 * Path类封装复合(多轮廓几何图形的路径
		 * 由直线段*、二次曲线,和三次方曲线，也可画以油画。drawPath(路径、油漆),要么已填充的或抚摸
		 * (基于油漆的风格),或者可以用于剪断或画画的文本在路径。
		 */

		// 画圆角矩形
		p.setStyle(Paint.Style.FILL);// 充满
		p.setColor(Color.LTGRAY);
		p.setAntiAlias(true);// 设置画笔的锯齿效果
		canvas.drawText("画圆角矩形:", 10, 260, p);
		RectF oval3 = new RectF(80, 260, 200, 300);// 设置个新的长方形
		canvas.drawRoundRect(oval3, 20, 15, p);// 第二个参数是x半径，第三个参数是y半径

		// 画贝塞尔曲线
		canvas.drawText("画贝塞尔曲线:", 10, 310, p);
		p.reset();
		p.setStyle(Paint.Style.STROKE);
		p.setColor(Color.GREEN);
		Path path2 = new Path();
		path2.moveTo(100, 320);// 设置Path的起点
		path2.quadTo(150, 310, 170, 400); // 设置贝塞尔曲线的控制点坐标和终点坐标
		canvas.drawPath(path2, p);// 画出贝塞尔曲线

		// 画点
		p.setStyle(Paint.Style.FILL);
		canvas.drawText("画点：", 10, 390, p);
		canvas.drawPoint(60, 390, p);// 画一个点
		canvas.drawPoints(new float[] { 60, 400, 65, 400, 70, 400 }, p);// 画多个点

		// 画图片，就是贴图
		Bitmap bb = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		canvas.drawBitmap(bb, 250, 360, p);

		view.setImageBitmap(bitmap);
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
		mPaint.setTextSize(30);
		mPaint.setColor(Color.BLACK);

		String aboutTheGame = "【新技能get√】微信扫描下方二维码关注“妙队”公众账号， 一扫下载我们的手机客户端，随时随地查看自己的排队情况，再也不用被美食“绑架”了 ";
		int tox =10,toy=20;
		float textsize=(float) 30.0;
		
		writerText(mCanvas,tox,toy,textsize,aboutTheGame);
		// 二维码
		Bitmap viewBg = BitmapFactory.decodeResource(getResources(), R.drawable.bar2);
		Matrix matrix = new Matrix();
		int width = viewBg.getWidth();// 获取资源位图的宽	
		int height = viewBg.getHeight();// 获取资源位图的高
		float w = (float) 200 / viewBg.getWidth();
		float h = (float) 200 / viewBg.getHeight();
		matrix.postScale(w, h);// 获取缩放比例
		Bitmap tiger = Bitmap.createBitmap(viewBg, 0, 0, width, height, matrix, true);
		mCanvas.drawBitmap(tiger, (384-200)/2, 220, mPaint);

		//3.餐厅名字,桌型及 排单号码
		mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
		mPaint.setTextSize(40);
		mCanvas.drawText("关于吃.com", 0, 220+240, mPaint);
		mCanvas.drawText("小桌(3-4人)    B26号", 0, 220+240+60, mPaint);
		
		// 5.等待人数/电话等信息
		mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
		mPaint.setTextSize(20);
		mCanvas.drawText("人数：3", 10, 220+240+40+70, mPaint);
		mCanvas.drawText("电话：暂无 ", 10, 220+240+40+60+40, mPaint);

//		view.setImageBitmap(bitmap);
		// --------------------------尝试 加载 layout 生成 图片--------------------------
		// View convertview =
		// LayoutInflater.from(this).inflate(R.layout.layout_queue, null);
		// convertview.setDrawingCacheEnabled(true);
		// view.buildDrawingCache();
		// setContentView(convertview);
		// Bitmap b1 = convertview.getDrawingCache();

		if (bitmap != null) {
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
