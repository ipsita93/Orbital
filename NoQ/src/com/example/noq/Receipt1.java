package com.example.noq;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

// This is Receipt1 page
public class Receipt1 extends Activity {

	protected static final int FLAG_ACTIVITY_REORDER_TO_FRONT = 131072;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// to click on clear all button
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
				}
			});
	       
	   	// to click on submit button
			final EditText et = (EditText) findViewById(R.id.editText3);
	        Button b2 = (Button) findViewById(R.id.button2);			
			
	        b2.setOnClickListener(new OnClickListener() {											
				@Override
				public void onClick(View arg0) {
					EditText receiptNum = (EditText) findViewById(R.id.editText1);
					EditText shopName = (EditText) findViewById(R.id.editText2);
					if (validateNum(receiptNum.getText().toString())){
						receiptNum.setError(null);
						if (validateShop(shopName.getText().toString())){
							shopName.setError(null);
							Intent intent = new Intent(Receipt1.this, Receipts.class); // Going to back Receipts
							intent.putExtra("amount", Double.parseDouble(et.getText().toString()));
							intent.putExtra("isValid", true);
							intent.setFlags(FLAG_ACTIVITY_REORDER_TO_FRONT); 
							// bring an existing instance of the called activity type present in the current stack to the 
							// foreground instead of creating a new instance
							startActivity(intent);
						}
						else{
							shopName.setError("Invalid shop");
						}
					}
					else{
						receiptNum.setError("Invalid receipt code");
					}
				}
			}); 
	        
	} // end of onCreate()
	
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// returns true if receipt number is valid
	// right now assume that the first character must be a letter 
	// followed by 3 numbers for receipt to be valid
	// to do: research on security, hashing, etc.
	private boolean validateNum(String receiptNum){
		char[] CreceiptNum = receiptNum.toCharArray();
		
		if (CreceiptNum.length != 4)
			return false;
		if (!Character.isLetter(CreceiptNum[0])) 
			return false;
		if (!Character.isDigit(CreceiptNum[1]) || 
			!Character.isDigit(CreceiptNum[2]) || !Character.isDigit(CreceiptNum[3]))
			return false;
		return true;
	}
	
	// returns true if shop is valid
	// right now assume only 3 shops called abc, def and ghi
	// to do: make a database of shops, ignore case and enhance searching
	private boolean validateShop(String SshopName){
		int numShops = 3;
		String[] shops = {"abc", "def", "ghi"};

		for (int i=0; i<numShops; i++){
			if (SshopName.equals(shops[i])){
				return true;
			}
		}	
		return false;
	}
}
