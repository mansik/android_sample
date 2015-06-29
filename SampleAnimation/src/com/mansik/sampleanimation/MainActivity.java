package com.mansik.sampleanimation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;


public class MainActivity extends Activity {

	public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
		
		private SurfaceHolder holder;
		private Thread thread;
		private int pos_x = 0;
		private Paint paint = new Paint();
		
		public MySurfaceView(Context context) {
			super(context);
			holder = getHolder();
			holder.addCallback(this);
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			thread = new Thread();
			thread.start();			
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			thread = null;			
		}

		@Override
		public void run() {
			paint.setTextSize(30);
			while(thread!=null){
				Canvas canvas = holder.lockCanvas();
				if(canvas == null)
					break;
				canvas.drawColor(Color.WHITE);
				canvas.drawText("jfkla", pos_x, 100, paint);
				holder.unlockCanvasAndPost(canvas);
				
				pos_x-=20;
				if(pos_x<0)
					pos_x=getWidth();
				try {
					Thread.sleep(100);
				}catch(Exception e){					
				}
			}
			
		}
		
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MySurfaceView(this));
	}

}
