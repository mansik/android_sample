package com.mansik.sampleiopreference;

import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private void writeToPrefs(String key, String value){
		SharedPreferences prefs = getSharedPreferences("myprefs",MODE_PRIVATE);
		
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key,value);
		editor.commit();
	}
	
	private String readFromPrefs(){
		StringBuilder sb = new StringBuilder();
		
		SharedPreferences prefs = getSharedPreferences("myprefs", MODE_PRIVATE);
		Map<String, ?> map = prefs.getAll();
		for(Entry<String, ?> entry:map.entrySet()){
			String key = entry.getKey();
			Object value = entry.getValue();
			sb.append(key+"|"+value.toString() + "\n");			
		}
		return sb.toString();			
	}
}
