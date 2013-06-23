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
		setContentView(R.layout.sign_up1);
		
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
				String nric = ((EditText)findViewById(R.id.editText2)).getText().toString();
				String contact = ((EditText)findViewById(R.id.editText3)).getText().toString();
				String email = ((EditText)findViewById(R.id.editText4)).getText().toString();
				String vehNum = ((EditText)findViewById(R.id.editText5)).getText().toString();
				String iu = ((EditText)findViewById(R.id.editText6)).getText().toString();
				String password = ((EditText)findViewById(R.id.editText7)).getText().toString();
				
				if (inputValidation()) {
					db.addUser(new User(1, name, nric, contact, email, vehNum, iu, password)); 
					Log.d("Insert: ", "Inserting...");
					Intent intent = new Intent(SignUp.this, HomePg.class);
					intent.putExtra("name", name);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivityForResult(intent, 1);
				}
			}
		});
	}
	
	private boolean inputValidation() {
		EditText pw1 = (EditText) findViewById(R.id.editText7);
		EditText pw2 = (EditText) findViewById(R.id.editText8);
		EditText et1 = (EditText) findViewById(R.id.editText1);
		EditText et2 = (EditText) findViewById(R.id.editText2);
		EditText et3 = (EditText) findViewById(R.id.editText3);
		EditText et4 = (EditText) findViewById(R.id.editText4);
		EditText et5 = (EditText) findViewById(R.id.editText5);
		EditText et6 = (EditText) findViewById(R.id.editText6);
		
		// if any of the required fields are left blank, there will be an error and the password fields will be cleared out. 
		if (et1.getText().length() == 0) {
			pw1.setText("");
			pw2.setText("");
			et1.setError("Required field cannot be left blank");
			return false;
		}
		
		if (et2.getText().length() == 0) {
			pw1.setText("");
			pw2.setText("");
			et4.setError("Required field cannot be left blank");
			return false;
		}
		
		if (et3.getText().length() == 0) {
			pw1.setText("");
			pw2.setText("");
			et3.setError("Required field cannot be left blank");
			return false;
		}
		
		if (et4.getText().length() == 0) {
			pw1.setText("");
			pw2.setText("");
			et2.setError("Required field cannot be left blank");
			return false;
		}
		
		if (et5.getText().length() == 0) {
			pw1.setText("");
			pw2.setText("");
			et5.setError("Required field cannot be left blank");
			return false;
		}
		
		if (et6.getText().length() == 0) {
			pw1.setText("");
			pw2.setText("");
			et6.setError("Required field cannot be left blank");
			return false;
		}
		// if either the passwords typed don't match or either password field is left blank, show an error. 
		if (pw1.getText().length() == 0) {
			pw1.setText("");
			pw2.setText("");
			pw1.setError("Required field cannot be left blank");
			return false;
		}
		if (pw2.getText().length() == 0) {
			pw1.setText("");
			pw2.setText("");
			pw2.setError("Required field cannot be left blank");
			return false;
		}
		if (!pw1.getText().toString().equals(pw2.getText().toString())) {
			pw1.setText("");
			pw2.setText("");
			pw1.setError("Passwords do not match");
			pw2.setError("Passwords do not match");
			return false;
		}
		return true;
	
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
