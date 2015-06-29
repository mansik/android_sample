package com.mansik.samplelistview;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements
		ListView.OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ListView
		int sli = android.R.layout.simple_list_item_1;
		ArrayAdapter<String> adL = new ArrayAdapter<String>(this, sli);
		adL.add("Add");
		adL.add("Delete");
		adL.add("Call");
		adL.add("Camera");
		adL.add("Day");
		adL.add("Directions");

		ListView lv = new ListView(this);
		lv.setBackgroundColor(Color.WHITE);
		lv.setAdapter(adL);
		lv.setOnItemClickListener(this);
		setContentView(lv);
	}

	// listview
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setMessage(((TextView) view).getText());
		ad.setPositiveButton("OK", null);
		ad.show();

	}
}
