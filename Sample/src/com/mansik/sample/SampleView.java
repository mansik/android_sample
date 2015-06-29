package com.mansik.sample;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.GetChars;
import android.view.MotionEvent;
import android.view.View;

public class SampleView extends View{
	private Paint paint = new Paint();
	private Path path = new Path();
	private Bitmap bmp;
	private ArrayList<PointF> points = new ArrayList<PointF>();
	
			
	public SampleView(Context context) {
		super(context);		
		setBackgroundColor(Color.WHITE);
		//이미지 리소스 가져오기
		Resources res = context.getResources();
		bmp = BitmapFactory.decodeResource(res, R.drawable.ugc);
		
		//터치 이벤트
		setFocusable(true);
	}
	
	@Override
	public void onDraw(Canvas canvas){
		myDrawText(canvas);
		myDrawLine(canvas);
		myDrawRect(canvas);
		myDrawCircle(canvas);
		myDrawArc(canvas);
		myDrawBitmap(canvas);
		myTouchCircle(canvas);
	}
	
	
	public boolean onTouchEvent(MotionEvent event)
	{
		int action = event.getAction();
		//int ptcnt=event.getPointerCount();
		if ((action & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN){
			points.add(new PointF(event.getX(),event.getY())); //좌표를 ArrayList에 저장
			invalidate();//화면 다시 그리기			
		}
		
		return true;
	}
	
	private void myTouchCircle(Canvas canvas)
	{
		for(int i =0;i<points.size();i++){
			canvas.drawCircle(points.get(i).x,points.get(i).y , 3, paint);
		}
	}
	
	private void myDrawBitmap(Canvas canvas)
	{
		canvas.drawBitmap(bmp, 500, 200, paint);
	}
	
	private void myDrawArc(Canvas canvas)
	{
		paint.reset();
		RectF rectf= new RectF(400,400,600,600);
		canvas.drawArc(rectf, 30F, 120F,false, paint);
	}
	
	private void myDrawCircle(Canvas canvas)
	{
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.GREEN);
		canvas.drawCircle(500, 400, 100, paint);				
	}
	
	private void myDrawRect(Canvas canvas)
	{
		
		Rect rect = new Rect(200,300,300,500);		
		canvas.drawRect(rect, paint);
		
		//canvas.drawRect(5, 5, 105, 150, paint);
	}
			
	private void myDrawLine(Canvas canvas)
	{
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(4);
		
		path.moveTo(30, 300);
		path.lineTo(60, 350);
		path.lineTo(150, 500);
		canvas.drawPath(path, paint);
		
		//canvas.drawLine(10, 50,150, 280, paint);
	}
	
	private void myDrawText(Canvas canvas) 
	{		
		paint.setTextSize(50);
				
		paint.setColor(Color.rgb(255, 0, 50));
		canvas.drawText("hellow!!!", 0, 50, paint);
		
		paint.setTypeface(Typeface.DEFAULT_BOLD);		
		paint.setColor(Color.argb(200, 80, 0, 50));
		canvas.drawText("hellow!!!", 0, 100, paint);
		
		paint.setTextSkewX(-0.25f);
		canvas.drawText("hellow!!!", 0, 150, paint);
		
		paint.setFakeBoldText(true);
		paint.setTextSkewX(0);
		canvas.drawText("hhh헤ㅐㄹ한글이다.!",20,200,paint);
	}
}
