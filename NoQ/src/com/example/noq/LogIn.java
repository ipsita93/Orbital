package com.example.noq;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LogIn extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		
		clearAll();
		
	}
	private void clearAll() {
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				EditText et1 = (EditText) findViewById(R.id.editText1);
				et1.setText("");
				EditText et2 = (EditText) findViewById(R.id.editText2);
				et2.setText("");
			}
		});	
	
	}
}
