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
	} 

	private void customUserName() {
		
		TextView name = (TextView) findViewById(R.id.textView2);
		name.append(", !");
		// TO DO: WL!! Please access the name from the db and append here! 
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

