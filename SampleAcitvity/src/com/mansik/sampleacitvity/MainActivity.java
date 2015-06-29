package com.mansik.sampleacitvity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.WHITE);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);

		int wc = LinearLayout.LayoutParams.WRAP_CONTENT;
		Button btn = new Button(this);
		btn.setText("Push");
		btn.setTag("btn1");
		btn.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		btn.setOnClickListener(this);
		layout.addView(btn);

		tv = new TextView(this);
		tv.setText("???");
		tv.setLayoutParams(new LinearLayout.LayoutParams(wc, wc));
		layout.addView(tv);

		Button btnIe = new Button(this);
		btnIe.setText("call ie");
		btnIe.setTag("btnIe");
		btnIe.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		btnIe.setOnClickListener(this);
		layout.addView(btnIe);
		
		Button btnMap = new Button(this);
		btnMap.setText("call Map");
		btnMap.setTag("btnMap");
		btnMap.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		btnMap.setOnClickListener(this);
		layout.addView(btnMap);
		
		Button btnDial = new Button(this);
		btnDial.setText("call Dial");
		btnDial.setTag("btnDial");
		btnDial.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		btnDial.setOnClickListener(this);
		layout.addView(btnDial);
		
		Button btnSetting = new Button(this);
		btnSetting.setText("call setting");
		btnSetting.setTag("btnSetting");
		btnSetting.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		btnSetting.setOnClickListener(this);
		layout.addView(btnSetting);
	}

	// 1. putExtra , startActivity
	@Override
	public void onClick(View v) {

		String tag = (String) v.getTag();
		if (tag == "btn1") {
			Intent it = new Intent(this, SubActivity.class);
			it.putExtra("param", "ABC");
			startActivityForResult(it, 0);
			// startActivity(it);
		} else if (tag == "btnIe") {
			Uri uri = Uri.parse("http://www.google.co.kr");
			Intent it2 = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(it2);
		} else if (tag == "btnMap"){
			Uri uri3 = Uri.parse("geo:0,0?q=Seoul");
			Intent it3 = new Intent(Intent.ACTION_VIEW,uri3);
			startActivity(it3);
		} else if (tag == "btnDial"){
			Uri uri4 = Uri.parse("tel:114");
			Intent it4 = new Intent(Intent.ACTION_VIEW,uri4);
			startActivity(it4);
		} else if (tag == "btnSetting"){
			Intent it5 = new Intent(Settings.ACTION_SETTINGS);
			startActivity(it5);
		}
	}

	// 4. getExtras()
	@Override
	protected void onActivityResult(int reqcode, int resultcode, Intent it) {
		if (reqcode == 0 && resultcode == Activity.RESULT_OK) {
			Bundle extras = it.getExtras();
			if (extras != null) {
				tv.setText(extras.getString("return"));
			}
		}
	}

}
