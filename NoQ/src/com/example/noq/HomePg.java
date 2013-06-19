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

	// To make sure getIntent() always return the most recent intent
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
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
		TextView name = (TextView) findViewById(R.id.textView2);
		onNewIntent(getIntent());
		name.append(", ");
		name.append(getIntent().getCharSequenceExtra("name"));
		name.append("!");
	}
	
	private void redeemToday() {
		Button redeemToday = (Button) findViewById(R.id.button1);
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

