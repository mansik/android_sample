package com.mansik.samplelayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity implements View.OnClickListener,
		CompoundButton.OnCheckedChangeListener,
		RadioGroup.OnCheckedChangeListener, Spinner.OnItemSelectedListener,
		EditText.OnEditorActionListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		// Layout
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.WHITE);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);

		int wc = LinearLayout.LayoutParams.WRAP_CONTENT;

		// button
		Button btn = new Button(this);
		btn.setText("Push");
		btn.setTag("btn1");
		btn.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		btn.setOnClickListener(this); // btn ctl에 이벤트 핸들러 연결
		layout.addView(btn);

		// textview
		TextView tv = new TextView(this);
		tv.setText("???");
		tv.setTag("tv1");
		layout.addView(tv);

		// checkbox
		CheckBox chk = new CheckBox(this);
		chk.setText("Check");
		chk.setChecked(true);
		chk.setTag("chk1");
		chk.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		chk.setOnCheckedChangeListener(this); // chk ctl에 이벤트 핸들러 연결
		layout.addView(chk);

		// radio button
		RadioButton rb1 = new RadioButton(this);
		rb1.setId(0);
		rb1.setText("Radio Button -1");

		RadioButton rb2 = new RadioButton(this);
		rb2.setId(1);
		rb2.setText("Radio Button -2");

		RadioGroup rg = new RadioGroup(this);
		rg.addView(rb1);
		rg.addView(rb2);
		rg.check(0);
		rg.setTag("rg1");
		rg.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		rg.setOnCheckedChangeListener(this); // rg ctl에 이벤트 핸들러 연결
		layout.addView(rg);

		// spinner 사용하기 위한 설정
		int ssi = android.R.layout.simple_spinner_item;
		int ssdi = android.R.layout.simple_spinner_dropdown_item;
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this, ssi);
		ad.setDropDownViewResource(ssdi);
		ad.add("SP1");
		ad.add("SP2");
		ad.add("SP3");

		// spinner
		Spinner sp = new Spinner(this);
		sp.setAdapter(ad);
		sp.setSelection(0);
		sp.setTag("sp1");
		sp.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		sp.setOnItemSelectedListener(this); // sp ctl에 이벤트 핸들러 연결
		layout.addView(sp);

		// edittext
		EditText ed = new EditText(this);
		ed.setText("Edit");
		ed.setTag("ed1");
		ed.setInputType(InputType.TYPE_CLASS_TEXT);
		ed.setLayoutParams(new LinearLayout.LayoutParams(300, wc));
		ed.setOnEditorActionListener(this);
		layout.addView(ed);

		// optionmenu를 actionBar에 표시
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	}

	// optionmenu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuItem i0 = menu.add(0, 0, 0, "Add");
		i0.setIcon(android.R.drawable.ic_menu_add);

		MenuItem i1 = menu.add(0, 1, 0, "Delete");
		i1.setIcon(android.R.drawable.ic_menu_delete);
		// optionmenu를 actionBar에 표시
		i1.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);

		return true;
	}

	// optionmenu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		String text = "";
		switch (id) {
		case 0:
			text = "Add";
			break;
		case 1:
			text = "Del";
			break;
		default:
			return false;
		}

		AlertDialog.Builder ad = new AlertDialog.Builder(this);
		ad.setMessage(text);
		ad.setPositiveButton("OK", null);
		ad.show();

		return true;
	}

	// textview
	@Override
	public void onClick(View v) {
		String tag = (String) v.getTag();
		if (tag == "btn1") {
			View p = (View) v.getParent();
			TextView tv = (TextView) p.findViewWithTag("tv1");
			tv.setText("boom!!");
		}
	}

	// ckeckbutton
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		String tag = (String) buttonView.getTag();
		if (tag == "chk1") {
			View p = (View) buttonView.getParent();
			TextView tv = (TextView) p.findViewWithTag("tv1");
			tv.setText(isChecked ? "check!" : "unchecked!");
		}
	}

	// radtiobutton
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		String tag = (String) group.getTag();
		if (tag == "rg1") {
			View p = (View) group.getParent();
			TextView tv = (TextView) p.findViewWithTag("tv1");
			tv.setText("Radio-" + checkedId);
		}

	}

	// spinner
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		String tag = (String) parent.getTag();
		if (tag == "sp1") {
			View p = (View) parent.getParent();
			TextView tv = (TextView) p.findViewWithTag("tv1");
			tv.setText(((TextView) view).getText() + "[" + position + "]" + id);
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	// edittext
	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		String tag = (String) v.getTag();
		if (tag == "ed1") {
			View p = (View) v.getParent();
			TextView tv = (TextView) p.findViewWithTag("tv1");
			tv.setText(v.getText());
		}
		return false;
	}

}
