package com.example.noq;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SignUp extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		
		clearAll();
		
		final CheckBox responseCheckbox = (CheckBox) findViewById(R.id.checkBox1);  
		boolean requiresEmail = responseCheckbox.isChecked();
		// TO DO: send an email? or else remove this! 
		if (requiresEmail) {
		}
		
		
		
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
				EditText et3 = (EditText) findViewById(R.id.editText3);
				et3.setText("");
				EditText et4 = (EditText) findViewById(R.id.editText3);
				et4.setText("");
				EditText et5 = (EditText) findViewById(R.id.editText3);
				et5.setText("");
				EditText et6 = (EditText) findViewById(R.id.editText3);
				et6.setText("");
				EditText et7 = (EditText) findViewById(R.id.editText3);
				et7.setText("");
			}
		});	
	
	}
}
