package com.example.noq;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class SignUp extends Activity {
	
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	EditText name; 
	EditText nric;
	EditText contact;
	EditText email; 
	EditText vehNum; 
	EditText iu;
	EditText password1;
	EditText password2;
	
	// url to create a new user
	private static String url_create_user = "http://192.168.1.7/android_connect/create_user.php";
	
	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up1);
		
		name = (EditText) findViewById(R.id.vehnumber);
		nric = (EditText) findViewById(R.id.password);
		contact = (EditText) findViewById(R.id.amtSpent);
		email = (EditText) findViewById(R.id.editText4);
		vehNum = (EditText) findViewById(R.id.editText5);
		iu = (EditText) findViewById(R.id.editText6);
		password1 = (EditText) findViewById(R.id.editText7);
		password2 = (EditText) findViewById(R.id.editText8);
		
		name.setCursorVisible(true);
		clearAll(); 
		signUp();
	} 	
	
	// sets up the sign up button
	private void signUp() {
		Button signup = (Button) findViewById(R.id.button2);
		signup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {				
				if (inputValidation()) {
					new CreateNewUser().execute();
					Intent intent = new Intent(SignUp.this, HomePg.class); // Going to Home page 
					intent.putExtra("name", name.getText().toString());
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivityForResult(intent, 1);
				}
			}
		});
	}
	
	private boolean inputValidation() {
		// if any of the required fields are left blank, there will be an error and the password fields will be cleared out. 
		if (name.getText().length() == 0) {
			password1.setText("");
			password2.setText("");
			name.setError("Required field cannot be left blank");
			return false;
		}
		
		if (nric.getText().length() == 0) {
			password1.setText("");
			password2.setText("");
			nric.setError("Required field cannot be left blank");
			return false;
		}
		
		if (contact.getText().length() == 0) {
			password1.setText("");
			password2.setText("");
			contact.setError("Required field cannot be left blank");
			return false;
		}
		
		if (email.getText().length() == 0) {
			password1.setText("");
			password2.setText("");
			email.setError("Required field cannot be left blank");
			return false;
		}
		
		if (vehNum.getText().length() == 0) {
			password1.setText("");
			password2.setText("");
			vehNum.setError("Required field cannot be left blank");
			return false;
		}
		
		if (iu.getText().length() == 0) {
			password1.setText("");
			password2.setText("");
			iu.setError("Required field cannot be left blank");
			return false;
		}
		// if either the passwords typed don't match or either password field is left blank, show an error. 
		if (password1.getText().length() == 0) {
			password1.setText("");
			password2.setText("");
			password1.setError("Required field cannot be left blank");
			return false;
		}
		if (password2.getText().length() == 0) {
			password1.setText("");
			password2.setText("");
			password2.setError("Required field cannot be left blank");
			return false;
		}
		if (!password1.getText().toString().equals(password2.getText().toString())) {
			password1.setText("");
			password2.setText("");
			password1.setError("Passwords do not match");
			password2.setError("Passwords do not match");
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
				name.setText("");
				nric.setText("");
				contact.setText("");
				email.setText("");
				vehNum.setText("");
				iu.setText("");
				password1.setText("");
				password2.setText("");
			}
		});	
	
	}
	
	// Background Async Task to create new user
	private class CreateNewUser extends AsyncTask<String, String, String> {
		// Before starting background thread Show Progress Dialog
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			pDialog = new ProgressDialog(SignUp.this);
			pDialog.setMessage("Creating User...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		
		// Creating user
		protected String doInBackground(String... args){ 
			String sname = name.getText().toString(); 
			String snric = nric.getText().toString(); 
			String scontact = contact.getText().toString(); 
			String semail = email.getText().toString(); 
			String svehNum = vehNum.getText().toString(); 
			String siu = iu.getText().toString(); 
			String spassword = password1.getText().toString(); 
			
			// Building parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", sname));
            params.add(new BasicNameValuePair("nric", snric));
            params.add(new BasicNameValuePair("contact_num", scontact));
			params.add(new BasicNameValuePair("email", semail));
			params.add(new BasicNameValuePair("vehicle_num", svehNum));
			params.add(new BasicNameValuePair("iu_num", siu));
			params.add(new BasicNameValuePair("password", spassword));
	
			// Getting JSON object, null pointer here as json is null from JSONParser
			JSONObject json = jsonParser.makeHttpRequest(url_create_user, "POST", params);

			// Check log for response
			Log.d("Create Response", json.toString());
			
			// Check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);
				if (success == 1){
					Intent intent = new Intent(SignUp.this, HomePg.class);
					intent.putExtra("name", sname);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivityForResult(intent, 1);
				}
				else{
					// failed to add user
				}
			} catch (JSONException e){
				e.printStackTrace();
			}
			
			return null;
		}
		
		// After completing background task, dismiss the progress dialog
		protected void onPostExecute(String file_url){
			// dismiss the dialog
			pDialog.dismiss();
		}
	}
}
