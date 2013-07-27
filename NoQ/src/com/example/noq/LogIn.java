package com.example.noq;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smartmobilesofware.ocrapiservice.OCR1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.DialogInterface;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.util.Log;

public class LogIn extends Activity {
	
	private ProgressDialog pDialog;	
	JSONParser jsonParser = new JSONParser();
	EditText vehNum;
	EditText password;
	boolean liFail = false;
	
	// url to create a new user
	private static final String url_user_details = "http://192.168.1.7/android_connect/get_user_details.php";
	
	// JSON node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_USER = "user";
	private static final String TAG_NAME = "name";
	private static final String TAG_VEHNUM = "vehicle_num";
	private static final String TAG_PASSWORD = "password";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);

		vehNum = (EditText) findViewById(R.id.vehnumber);
		password = (EditText) findViewById(R.id.password);
		
		signUp();
		clearAll();
		logIn();	
	}
	
	//Override of hardware back button
	@Override 
	public void onBackPressed() {
		Intent closeIntent = new Intent(Intent.ACTION_MAIN);
		closeIntent.addCategory(Intent.CATEGORY_HOME);
		closeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(closeIntent);
	} 
	
	// activity is partially visible
	public void onPause() {
	    super.onPause();  // Always call the superclass method first    
	} // end of onPause    
	
	// called by system when first creating activity as well as when resuming from 'paused' state
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	}// end of onResume
	
	// called by system when second activity is created
	protected void onStop() {
	    super.onStop();  // Always call the superclass method first	    
	} // end of onStop
	
	// To make sure getIntent() always return the most recent intent
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    // Activity being restarted from stopped state    
	protected void onRestart() {
	    super.onRestart();  // Always call the superclass method first
	    onNewIntent(getIntent());
		
		liFail = false;
		
		final CheckBox rm = (CheckBox) findViewById(R.id.rmbMe);
		if (!rm.isChecked()){
		vehNum.setText("");
		password.setText("");
		}
	}
	
	private void signUp() {
		Button newUser = (Button) findViewById(R.id.signUp);
		newUser.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LogIn.this, SignUp.class); // Going to SignUp page
				startActivity(intent);
				vehNum.setText("");
				password.setText("");
				// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			}
		});	
	}
	
	private void logIn() {
		Button logIn = (Button) findViewById(R.id.button2);
		logIn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {				
//				new CheckUserDetails().execute();
				if (vehNum.getText().toString().equals("SDA1234A") && password.getText().toString().equals("password")){
					Intent intent = new Intent(LogIn.this, HomePg.class); // Going to Home page 
					intent.putExtra("name", "Peter Pan"); // changed back to uName
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivityForResult(intent, 1);						
				}
				else{
					vehNum.setText("");
					password.setText("");
					
					AlertDialog.Builder failedLogin = new AlertDialog.Builder(LogIn.this);
					failedLogin.setMessage("Log in has failed!");
					failedLogin.setPositiveButton("Sign up!", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface register, int id){
							Intent su = new Intent(LogIn.this, SignUp.class);
							su.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivityForResult(su, 1);	
						}
					})
					.setNegativeButton("Try again!", new DialogInterface.OnClickListener(){
						public void onClick(DialogInterface cancel, int id){
							if (id == Dialog.BUTTON_NEGATIVE)
								cancel.dismiss();
						}
					});
					failedLogin.show();				
				}
			}
		});	
	}
	
	private void clearAll() {
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				vehNum.setText("");
				password.setText("");
			}
		});	
	
	}
/*	
	// Background Async Task to check user log in information
	class CheckUserDetails extends AsyncTask<String, String, String>{
		String uName = "";
		
		// Before starting background thread Show Progress Dialog
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			pDialog = new ProgressDialog(LogIn.this);
			pDialog.setMessage("Logging in...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args){ 
			String svehNum = vehNum.getText().toString();
			String spassword = password.getText().toString();

			// Building parameters			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("vehicle_num", svehNum));
            params.add(new BasicNameValuePair("password", spassword));
			
			// Getting JSON object, null pointer here as json is null from JSONParser
			JSONObject json = jsonParser.makeHttpRequest(url_user_details, "GET", params);	

			// Check log for response
			Log.d("Create Response", json.toString());

			// Check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);
				if (success == 1){
					// successfully received user details
					liFail = false;						
					
					JSONArray userObj = json.getJSONArray(TAG_USER); // JSON Array

					// get first product object from JSON Array
					JSONObject user = userObj.getJSONObject(0);
					uName = user.getString(TAG_NAME);
				}
				else{
					liFail = true;
					return null;
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

			if (liFail){
				// failed to log in, a dialog pops up and asks if want to try again or sign up
				vehNum.setText("");
				password.setText("");
				
				AlertDialog.Builder failedLogin = new AlertDialog.Builder(LogIn.this);
				failedLogin.setMessage("Log in has failed!");
				failedLogin.setPositiveButton("Sign up!", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface register, int id){
						Intent su = new Intent(LogIn.this, SignUp.class);
						su.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivityForResult(su, 1);	
					}
				})
				.setNegativeButton("Try again!", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface cancel, int id){
						if (id == Dialog.BUTTON_NEGATIVE)
							cancel.dismiss();
					}
				});
				failedLogin.show();
			}
			else{
				Intent intent = new Intent(LogIn.this, HomePg.class); // Going to Home page 
				intent.putExtra("name", uName);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivityForResult(intent, 1);				
			}
		}
	}
*/
}
