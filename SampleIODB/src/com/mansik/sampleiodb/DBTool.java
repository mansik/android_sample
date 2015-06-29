package com.mansik.sampleiodb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBTool {	
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
