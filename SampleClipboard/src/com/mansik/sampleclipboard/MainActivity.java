package com.mansik.sampleclipboard;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private ClipboardManager cmttext = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
	private ClipboardManager cmuri = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
	private ClipboardManager cmintent = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private void WriteClipText() {
		String text1 = "Test";
		ClipData ctext1 = ClipData.newPlainText("TEXTLABEL", text1);
		cmttext.setPrimaryClip(ctext1);
	}

	private void ReadClipText() {		
		ClipData ctext2 = cmttext.getPrimaryClip();
		String text2 = ctext2.getItemAt(0).getText().toString();
	}

	private void WriteClipUri() {
		String utext1 = "http://www.daum.net";
		Uri uri1 = Uri.parse(utext1);

		ClipData curi1 = ClipData
				.newUri(getContentResolver(), "URILABEL", uri1);
		cmuri.setPrimaryClip(curi1);
	}

	private void ReadClipUri() {		
		ClipData curi2 = cmuri.getPrimaryClip();
		Uri uri2=curi2.getItemAt(0).getUri();
		String text2= uri2.toString();
	}
	
	private void WriteClipIntent(){
		String itext1="text";
		Intent intent1 = new Intent();
		intent1.putExtra("param", itext1);
		ClipData cintent1 = ClipData.newIntent("INTENTLABEL", intent1);
		cmintent.setPrimaryClip(cintent1);
	}
	private void ReadClipIntent(){
		ClipData cintent2 = cmuri.getPrimaryClip();
		Intent intent2 = cintent2.getItemAt(0).getIntent();
		String text2= intent2.getExtras().getString("param").toString();
	}
}
