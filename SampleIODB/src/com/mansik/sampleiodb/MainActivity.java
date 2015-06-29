package com.mansik.sampleiodb;

import android.R.integer;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity{
	Button btnInsert  =null;
	Button btnUpdate = null;
	Button btnDelete =null;
	Button btnSelect =null;
	
	EditText etId = null;
	EditText etData = null;
	TextView tv = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnInsert = (Button)findViewById(R.id.btnInsert);
		btnUpdate= (Button)findViewById(R.id.btnUpdate);
		btnDelete= (Button)findViewById(R.id.btnDelete);
		btnSelect= (Button)findViewById(R.id.btnSelect);
		
		etId = (EditText)findViewById(R.id.etId);
		etData = (EditText) findViewById(R.id.etData);
		
		
		btnInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//insert(v.getContext(),Integer.parseInt(etId.getText().toString()), etData.getText().toString());
				
			}
		});
		
		btnSelect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//select(v.getContext());
			}
		});
		
	}
	
	DBHelper helper;
	SQLiteDatabase db;
	ContentValues vals;
		
	private void insert(Context context, int id, String data){
		helper = new DBHelper(context);
		db = helper.getWritableDatabase();
		
		vals = new ContentValues();
		vals.put("id", id);
		vals.put("data", data);
		db.insert(helper.TABLENAME, null, vals);	
		db.close();
	}
	
	private void update(Context context, int id, String data, int orgid){
		helper = new DBHelper(context);
		db = helper.getWritableDatabase();
		
		vals = new ContentValues();
		vals.put("id",id);
		vals.put("data", data);		
		String whereClause = "id = " + orgid ;		
		db.update(helper.TABLENAME, vals, whereClause, null);
		db.close();
	}
	
	private void delete(Context context, int id){		
		helper = new DBHelper(context);
		db = helper.getWritableDatabase();
		String whereClause= "id = " + id;
		db.delete(helper.TABLENAME, whereClause, null);
		db.close();
	}
	
	private String select(Context context){
		String[] columns = {"id","data"};
		String selection=null;
		String[] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = null;
		
		Cursor cur = null;
		StringBuilder sb = new StringBuilder();
		
		helper = new DBHelper(context);
		db = helper.getReadableDatabase();
		
		
		try{			
			cur = db.query( helper.TABLENAME, columns, selection, selectionArgs, groupBy, having, orderBy);
			while(cur.moveToNext()){
				sb.append(cur.getInt(0) + "," +cur.getString(1)+"\n" );
			}
		} finally {
			if(cur!=null) cur.close();
			db.close();
		}
		
		
		return sb.toString();
	}
	
}
