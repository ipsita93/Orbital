package com.example.noq;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Receipts extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receipts);
		
		// clicking on receipt1 button 
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Receipts.this, Receipt1.class); // Going to Receipt1
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivityForResult(intent, 1);
			}
	
		});
		
		// clicking on receipt2 button 
		Button b2 = (Button) findViewById(R.id.button2);
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Receipts.this, Receipt2.class);
				startActivity(intent);
			}
		});
		
		// clicking on receipt3 button 
		Button b3 = (Button) findViewById(R.id.button3);
		b3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Receipts.this, Receipt3.class);
				startActivity(intent);
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
	    
	    // changes the bluebox image to tickinbox image  
	 	if (getIntent().getBooleanExtra("isValid1", false)) {
	 		ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
	 		iv1.setImageResource(R.drawable.tick1);
	 	}
	 	if (getIntent().getBooleanExtra("isValid2", false)) {
	 		ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
	 		iv2.setImageResource(R.drawable.tick1);
	 	}
	 	if (getIntent().getBooleanExtra("isValid3", false)) {
	 		ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
	 		iv3.setImageResource(R.drawable.tick1);
	 	}		
	 	
	 	updateAmt(getIntent());
	 	
	 	checkRedeemNow();
	}
	
	// To check if the total amount spent has exceed $100
	// If yes, then change button to "Redeem Now" and green
	private void checkRedeemNow() {
	 	TextView tv1 = (TextView) findViewById(R.id.textView2);
	 	Button b4 = (Button) findViewById(R.id.button4);
	 	if (Double.parseDouble(tv1.getText().toString()) >= 100.00) {	
	 		b4.setText("Redeem Now!");
	 		b4.setClickable(true);
	 		b4.setBackgroundColor(0xFF1BE04C);
	 	}
	}
	
	// To update the total spent
	private void updateAmt(Intent data) {
		TextView tv = (TextView) findViewById(R.id.textView2);
		double newAmt = data.getDoubleExtra("amount", 0.00);
		double amt = Double.parseDouble(tv.getText().toString());
		amt += newAmt;
		tv.setText(String.valueOf(amt));
	}
	
}
