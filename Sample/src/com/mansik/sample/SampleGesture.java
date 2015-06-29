package com.mansik.sample;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector;

/**
 * @author user
 *
 */
public class SampleGesture extends View 
	implements GestureDetector.OnGestureListener 
{
	private Paint paint = new Paint();
	private GestureDetector gesdet;
	private ArrayList<String> status = new ArrayList<String>();
	
	public SampleGesture(Context context) {
		super(context);
		setBackgroundColor(Color.WHITE);
		gesdet = new GestureDetector(context, this);
		setFocusable(true);		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		paint.setAntiAlias(true);
		paint.setTextSize(20);
		for(int i=0;i<Math.min(10, status.size());i++){
			if (i==30) {
				canvas.drawColor(Color.WHITE);
			}
			canvas.drawText(status.get(i),0,40+i*20, paint);
					
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		gesdet.onTouchEvent(event);
		return true;
		//return super.onTouchEvent(event);		
	}

	@Override
	public boolean onDown(MotionEvent e) {		
		status.add("onDown");
		invalidate();		
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		status.add("onShowPress");
		invalidate();		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		status.add("onSingleTapUp");
		invalidate();		
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		status.add("onScroll");
		invalidate();		
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		status.add("onLongPress");
		invalidate();		
		
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		status.add("onFling");
		invalidate();		
		return false;
	}

	
}
