package com.example.fileio;

import java.io.*;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity implements View.OnClickListener{
	EditText mEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mEdit = (EditText) findViewById(R.id.edittext1);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save:
			try{
				FileOutputStream fos = openFileOutput("test.txt",Context.MODE_WORLD_READABLE);
				String str = "Android File IO Test";
				fos.write(str.getBytes());
				fos.close();
				mEdit.setText("write success");						
			} catch (Exception e) {
			} 
						
			break;
		case R.id.load:
			try{
				FileInputStream fis = openFileInput("test.txt");
				byte[] data = new byte[fis.available()];
				while(fis.read(data)!=-1){}
				fis.close();
				mEdit.setText(new String(data));
			} catch (FileNotFoundException e) {
				mEdit.setText("File Not Found");
			} catch (Exception e){				
			}
			break;
		case R.id.loadres:
			try {
				InputStream fres = getResources().openRawResource(R.raw.restext1);
				byte[] data = new byte[fres.available()];
				while(fres.read(data)!=-1){}
				fres.close();
				mEdit.setText(new String(data));
			} catch (Exception e) {
			}
			break;
		case R.id.delete:
			if (deleteFile("test.txt")){
				mEdit.setText("delete success");
			} else {
				mEdit.setText("delete failed");
			}
			break;
		}
	}


	

}
