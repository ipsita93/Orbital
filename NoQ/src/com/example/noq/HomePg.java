package com.example.noq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HomePg extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_pg);
		
		customUserName();
		redeemToday();
		logOut();
	} 
	// activity is partially visible
	public void onPause() {
	    super.onPause();  // Always call the superclass method first
	    
	} // end of onPause    
		
	// called by system when first creating activity as well as when resuming from 'paused' state
	public void onResume() {
	    super.onResume();  // Always call the superclass method first
	}// end of onResume
	
	// called by system when another activity is created
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
	    
		redeemToday();
	}
    
	private void logOut() {
		Button logOut = (Button) findViewById(R.id.button4);
		logOut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomePg.this, LogIn.class); // Going to Home page 
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
				// bring an existing instance of the called activity type present in the current stack to the 
				// foreground instead of creating a new instance
				setResult(RESULT_OK, intent);   
				startActivity(intent);
				// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			}
		});
	}
	
	private void customUserName() {
		TextView Name = (TextView) findViewById(R.id.textView2);
		onNewIntent(getIntent());
		Name.append(", ");
		Name.append(getIntent().getStringExtra("name"));
		Name.append("!");
	}
	
	private void redeemToday() {
		// Returning to home page after redeeming today
		final Button redeemToday = (Button) findViewById(R.id.button1);
		if (getIntent().getBooleanExtra("claimToday", false)){
			redeemToday.setText("Redeemed");
			redeemToday.setEnabled(false);
		}
		// First time on homepage to redeem today
		else{	
			redeemToday.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {		
					Intent intent = new Intent(HomePg.this, Receipts.class); // Going to Home page 
					startActivity(intent);
					// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				}
			});
		}
	}
}

