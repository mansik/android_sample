package com.mansik.samplefileio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.WHITE);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);

		int wc = LinearLayout.LayoutParams.WRAP_CONTENT;
		Button btnFile = new Button(this);
		btnFile.setText("Write & Read");
		btnFile.setTag("WRITE_READ");
		btnFile.setLayoutParams(new LinearLayout.LayoutParams(wc, wc));
		btnFile.setOnClickListener(this);
		layout.addView(btnFile);

		EditText et = new EditText(this);
		et.setText("??");
		et.setTag("et");
		layout.addView(et);

		TextView tv = new TextView(this);
		tv.setText("???");
		tv.setTag("tv");
		layout.addView(tv);
	}

	@Override
	public void onClick(View v) {
		if ((String) v.getTag() == "WRITE_READ") {
			View parent = (View) v.getParent();
			EditText et = (EditText) parent.findViewWithTag("et");
			TextView tv = (TextView) parent.findViewWithTag("tv");
			try {
				writeToFile(this, et.getText().toString());
				tv.setText(readFromFile(this));
			} catch (Exception e) {
				tv.setText("Error:" + e.getMessage());
			}
		}

	}

	private void writeToFile(Context c, String s) throws Exception {
		byte[] data = s.getBytes();
		OutputStream stream = null;

		try {
			stream = c.openFileOutput("test.txt", Context.MODE_PRIVATE);
			stream.write(data, 0, data.length);
			stream.close();
		} catch (Exception e) {
			try {
				if (stream != null)
					stream.close();
			} catch (IOException e1) {
				throw e;
			}
		}

	}

	private String readFromFile(Context c) throws Exception {
		byte[] buf = new byte[100];
		InputStream stream = null;
		ByteArrayOutputStream stream2 = null;

		try {
			stream = c.openFileInput("test.txt");
			stream2 = new ByteArrayOutputStream();

			int size = stream.read(buf);
			while (size > 0) {
				stream2.write(buf, 0, size);
				size = stream.read(buf);
			}
			stream2.close();
			stream.close();
			String s = new String(stream2.toByteArray());
			return s;

		} catch (Exception e) {
			try {
				if (stream != null)
					stream.close();
			} catch (IOException e2) {
				throw e;
			}
		}
		return "";
	}

}
