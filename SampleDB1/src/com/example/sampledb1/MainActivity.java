package com.example.sampledb1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	WorldDBHelper mHelper;
	EditText mText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mHelper = new WorldDBHelper(this);
		mText = (EditText) findViewById(R.id.editText1);
	}
	@Override
	public void onClick(View v) {
		SQLiteDatabase db;
		ContentValues row;
		
		switch(v.getId()){
		case R.id.insert:
			db = mHelper.getWritableDatabase();
			
			//1. insert 메서드로 삽입
			row = new ContentValues();
			row.put("eng", "boy"); //컬럼명, data
			row.put("han", "머서마"); //컬럼명, data
			db.insert("dic", null, row);
			
			//2. SQL 명령으로 삽입
			db.execSQL("INSERT INTO dic VALUES (null,'girl','가시나');");
			db.execSQL("INSERT INTO dic VALUES (null,'man','남자');");
			
			mHelper.close();
			
			mText.setText("insert Success");
			break;
		case R.id.delete:
			db = mHelper.getWritableDatabase();
			
			//1. delete 메서드로 삭제
			db.delete("dic", null, null);
			
			//2. sql 명령으로 삭제
			db.execSQL("DELETE FROM dic WHERE eng = 'man';");
			
			mHelper.close();
			
			mText.setText("delete Succes");
			break;
		case R.id.update:
			db = mHelper.getWritableDatabase();
			
			//1. update 메서드로 갱신
			row = new ContentValues();
			row.put("han", "소년");
			db.update("dic", row, "eng = 'boy'", null);
			
			//2. sql 명령으로 갱신
			db.execSQL("UPDATE dic SET han = '소녀' WHERE eng = 'girl';");
			
			mHelper.close();
			
			mText.setText("Update Success");
			break;
		case R.id.select:
			db = mHelper.getReadableDatabase();
			Cursor cursor;
			
			//1. query 메서드로 읽기
			//cursor = db.query("dic", new String[] {"eng","han"}, null, null, null, null, null); 
			
			//2. SQL 명령으로 읽기
			cursor = db.rawQuery("SELECT eng, han FROM dic;", null);
			
			String result ="";
			while(cursor.moveToNext()){
				String eng = cursor.getString(0);
				String han = cursor.getString(1);
				
				result += (eng + " = " + han + "\n");
			}
			
			if (result.length() == 0 ){
				mText.setText("empty Set");
			} else {
				mText.setText(result);
			}
			
			cursor.close();
			mHelper.close();
			break;
		}
				
	}
}

class WorldDBHelper extends SQLiteOpenHelper {

	public WorldDBHelper(Context context) {
		super(context, "EngWord.db", null, 1);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE dic ( _id INTEGER PRIMARY KEY AUTOINCREMENT " 
					+ ",eng TEXT" 
					+ ",han TEXT"
					+ ");"
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS dic;");
		onCreate(db);

	}

}
