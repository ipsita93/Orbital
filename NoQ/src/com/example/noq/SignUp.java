package com.example.noq;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.util.Log;

public class SignUp extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		
		clearAll(); 
		signUp();
	} 	
	
	// sets up the sign up button
	private void signUp() {
		final DatabaseHandler db = new DatabaseHandler(this);
		
		Button signup = (Button) findViewById(R.id.button2);
		signup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String name = ((EditText)findViewById(R.id.editText1)).getText().toString();
				String nric = ((EditText)findViewById(R.id.editText4)).getText().toString();
				String contact = ((EditText)findViewById(R.id.editText3)).getText().toString();
				String email = ((EditText)findViewById(R.id.editText2)).getText().toString();
				String vehNum = ((EditText)findViewById(R.id.editText5)).getText().toString();
				String iu = ((EditText)findViewById(R.id.editText6)).getText().toString();
				String password = ((EditText)findViewById(R.id.editText7)).getText().toString();
				
				inputValidation();
				
				db.addUser(new User(1, name, nric, contact, email, vehNum, iu, password)); // error!!!
				Log.d("Insert: ", "Inserting...");
				Intent intent = new Intent(SignUp.this, Receipts.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivityForResult(intent, 1);
			}
		});
	}
	
	private void inputValidation() {
		EditText pw1 = (EditText) findViewById(R.id.editText7);
		EditText pw2 = (EditText) findViewById(R.id.editText8);
		
		// if either the passwords typed don't match or either password field is left blank, show an error. 
		if (pw1.getText().length() == 0) {
			pw1.setText("");
			pw2.setText("");
			pw1.setError("Required field cannot be left blank");
		}
		if (pw2.getText().length() == 0) {
			pw1.setText("");
			pw2.setText("");
			pw2.setError("Required field cannot be left blank");
		}
		if (!pw1.getText().toString().equals(pw2.getText().toString())) {
			pw1.setText("");
			pw2.setText("");
			pw1.setError("Passwords do not match");
			pw2.setError("");
		}
	
	}
	
	// sets up the clearAll button
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
				EditText et4 = (EditText) findViewById(R.id.editText4);
				et4.setText("");
				EditText et5 = (EditText) findViewById(R.id.editText5);
				et5.setText("");
				EditText et6 = (EditText) findViewById(R.id.editText6);
				et6.setText("");
				EditText et7 = (EditText) findViewById(R.id.editText7);
				et7.setText("");
			}
		});	
	
	}
}
