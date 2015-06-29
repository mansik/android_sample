package com.example.samplegallery;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements Gallery.OnItemClickListener {

	private ArrayList<Bitmap> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		list = new ArrayList<Bitmap>();
		Resources res = getResources();
		list.add(BitmapFactory.decodeResource(res, android.R.drawable.ic_menu_agenda));
		list.add(BitmapFactory.decodeResource(res, android.R.drawable.ic_menu_add));
		list.add(BitmapFactory.decodeResource(res, android.R.drawable.ic_menu_delete));
		list.add(BitmapFactory.decodeResource(res, android.R.drawable.ic_menu_more));
		
		Gallery ga = new Gallery(this);
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

	
}
