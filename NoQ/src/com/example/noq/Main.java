package com.example.noq;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button b = (Button) findViewById(R.id.button1);
	       b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					EditText et1 = (EditText) findViewById(R.id.editText1);
					et1.setText("");
					EditText et2 = (EditText) findViewById(R.id.editText2);
					et2.setText("");
					EditText et3 = (EditText) findViewById(R.id.editText3);
					et3.setText("");
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
