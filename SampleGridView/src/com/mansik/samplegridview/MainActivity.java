package com.mansik.samplegridview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class MainActivity extends Activity {
	private ArrayList<Bitmap> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		list = new ArrayList<Bitmap>();
		Resources res = getResources();
		list.add(BitmapFactory.decodeResource(res, android.R.drawable.ic_menu_add));
		list.add(BitmapFactory.decodeResource(res,android.R.drawable.ic_menu_delete));
		list.add(BitmapFactory.decodeResource(res,android.R.drawable.ic_menu_call));
		list.add(BitmapFactory.decodeResource(res,android.R.drawable.ic_menu_crop));
		list.add(BitmapFactory.decodeResource(res,android.R.drawable.ic_menu_day));
		
		
		GridView gv = new GridView(this);
		gv.setBackgroundColor(Color.WHITE);
		gv.setNumColumns(4);
		gv.setAdapter(new MyAdapter());
		setContentView(gv);
	}	
	
	private class MyAdapter extends BaseAdapter  {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				ImageView iv = new ImageView(MainActivity.this);
				iv.setLayoutParams(new GridView.LayoutParams(72,92));
				iv.setPadding(0, 0, 0, 20);
				iv.setImageBitmap(list.get(position));
				convertView=iv;
			}
			return convertView;
		}

	}
}

