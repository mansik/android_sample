package com.mansik.samplewebbrowsing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

	public class MyWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return false; // 현재 webview에 링크를 연다. true면 새창에 링크 뛰움
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			String s = "error:(" +errorCode + ")"+ description;
			Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
		}

	}
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		WebView wv = new WebView(this);
		WebSettings sets = wv.getSettings();
		sets.setJavaScriptEnabled(true);
		sets.setSupportZoom(false);		
		wv.setWebViewClient(new MyWebViewClient());		
		setContentView(wv);
		wv.loadUrl("http://www.daum.net");
	}
	
}
