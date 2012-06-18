package com.paraz.clipboardwebview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class ClipboardWebViewActivity extends Activity {
	private final String TAG = "ClipboardWebView";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		final WebView exampleWebView = (WebView) findViewById(R.id.exampleWebView);
		exampleWebView.loadUrl("file:///android_asset/content.html");
		exampleWebView.getSettings().setJavaScriptEnabled(true);

		final Handler handler = new Handler();
		
		final Object selectHandler = new Object() {
			public void select() {
				handler.post(new Runnable() {
					public void run() {
						Log.d(TAG, "selecting");
						Toast.makeText(ClipboardWebViewActivity.this, "Now select the text", Toast.LENGTH_SHORT).show();
						final KeyEvent shiftPressEvent = new KeyEvent(0, 0,
								KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT, 0, 0);
						shiftPressEvent.dispatch(exampleWebView);
					}
					
				});
			}
		};

		exampleWebView.addJavascriptInterface(selectHandler, "selectHandler");
	}
}