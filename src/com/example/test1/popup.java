package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class popup extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);
	
		Intent popup = getIntent();
		String display = popup.getStringExtra(Jsouptest.EXTRA_MESSAGE);
		TextView tv;
		tv = (TextView) findViewById(R.id.popup);
		tv.setText(display);
		
		
	}
	
}