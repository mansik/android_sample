package com.mansik.sampleiodb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	static final String DBNAME ="test.db";
	static final String TABLENAME = "TestTable";	

	public DBHelper(Context context) {
		super(context, DBNAME, null, 1);		
	}  

	@Override
	public void onCreate(SQLiteDatabase db) {
		String s = "CREATE TABLE IF NOT EXISTS " + TABLENAME
				+ "(id INT PRIMARY KEY, data TEXT)";
		db.execSQL(s);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String s = "DROP TABLE IF EXISTS " + TABLENAME;
		db.execSQL(s);
	}

}
