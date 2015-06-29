package com.mansik.samplevideo;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity implements OnClickListener {

	private VideoView vview = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.WHITE);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);

		Button btn1 = new Button(this);
		btn1.setText("Play Video");
		btn1.setTag("btn1");
		btn1.setOnClickListener(this);
		layout.addView(btn1);

		vview = new VideoView(this);
		vview.requestFocus();
		vview.setMediaController(new MediaController(this));
		layout.addView(vview);
	}

	@Override
	public void onClick(View v) {
		String tag = (String) v.getTag();
		File sdcard = Environment.getExternalStorageDirectory();

		if (tag == "btn1") {
			playVideo(sdcard + "/DCIM/Camera/20150414_120902.mp4");
		}
	}

	private void playVideo(String path) {
		try {
			vview.setVideoPath(path);
			vview.start();
		} catch (Exception e) {
			AlertDialog.Builder ad = new AlertDialog.Builder(this);
			ad.setMessage(e.getMessage());
			ad.setPositiveButton("OK", null);
			ad.show();

		}
	}

}
