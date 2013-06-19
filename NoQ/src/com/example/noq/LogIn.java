package com.example.noq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LogIn extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		
		signUp();
		clearAll();
		logIn();	
	}
	
	private void signUp() {
		Button newUser = (Button) findViewById(R.id.button3);
		newUser.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LogIn.this, SignUp.class); // Going to SignUp page
				startActivity(intent);
				EditText et1 = (EditText) findViewById(R.id.editText1);
				et1.setText("");
				EditText et2 = (EditText) findViewById(R.id.editText2);
				et2.setText("");
				// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			}
		});	
	}
	
	private void logIn() {
		Button logIn = (Button) findViewById(R.id.button2);
		logIn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LogIn.this, HomePg.class); // Going to Home page 
				startActivity(intent);
				// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				EditText et1 = (EditText) findViewById(R.id.editText1);
				et1.setText("");
				EditText et2 = (EditText) findViewById(R.id.editText2);
				et2.setText("");
			}
		});	
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
