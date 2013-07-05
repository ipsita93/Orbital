package com.example.noq;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class LogIn extends Activity {
	
	private ProgressDialog pDialog;	
	JSONParser jsonParser = new JSONParser();
	EditText vehNum;
	EditText password;
	String id;
	
	// url to create a new user
	private static final String url_user_details = "http://api.noq.info/android_connect/get_user_details.php";
	
	// JSON node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_USER = "user";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_NRIC = "nric";
	private static final String TAG_CONTACT = "contact_num";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_VEHNUM = "vehicle_num";
	private static final String TAG_IU = "iu_num";
	private static final String TAG_PASSWORD = "password";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);

		vehNum = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		
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
				
				Intent intent = new Intent(LogIn.this, HomePg.class); // Going to Home page 
				intent.putExtra("name", "human");
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivityForResult(intent, 1);

				vehNum.setText("");
				password.setText("");
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

/*	// Background Async Task to check user log in information
	class CheckUserDetails extends AsyncTask<String, String, String>{
		// Before starting background thread Show Progress Dialog
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			pDialog = new ProgressDialog(LogIn.this);
			pDialog.setMessage("Checking user details...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
		
		// Checking user details in background thread
		protected String doInBackground(String... params){
			// updating UI from Background Thread
			runOnUiThread(new Runnable(){
				// check for success tag
				int success;
				try{
					// building parameters N0T FINISHED
					String svehNum = vehNum.getText().toString();
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					
					params.add(new BasicNameValuePair("id", id);
					
					// getting user details by making HTTP request
					JSONObject json = jsonParser.makeHttpRequest{url_user_details, "GET", params);
					
					// check log for json response
					Log.d("Single user details", json.toString());
					
					// json success tag
					success = json.getInt(TAG_SUCCESS);
					if (success == 1){
						JSONArray userObj = json.getJSONArray(TAG_USER);
					}
					else{
						// user with the id is not found
					}
				} catch (JSONException e){
					e.printStackTrace();
				}
			});
			return null;
		}
		// After completing background task, dismiss the progress dialog
		protected void onPostExecute(String file_url){
			// dismiss the dialog
			pDialog.dismiss();
		}
	}*/
}
