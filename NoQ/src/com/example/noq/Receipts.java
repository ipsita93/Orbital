package com.example.noq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Receipts extends Activity {
	
	protected static final int FLAG_ACTIVITY_NEW_TASK = 268435456;

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
				intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
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
	    //TextView tv = (TextView) findViewById(R.id.textView2);
		//Double amt = Double.parseDouble((String) tv.getText());
	} // end of onStop
	
	  // Activity being restarted from stopped state    
	protected void onRestart() {
	    super.onRestart();  // Always call the superclass method first
	    
	    // changes the bluebox image to tickinbox image  
	 	if (true) {
	 		ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
	 		iv1.setImageResource(R.drawable.tickinbox);
	 	}
	 	if (true) {
	 		ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
	 		iv2.setImageResource(R.drawable.tickinbox);
	 	}
	 	if(true) {
	 		ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
	 		iv3.setImageResource(R.drawable.tickinbox);
	 	}		
	 	
	 	// updateAmt();
	 	
	 	TextView tv1 = (TextView) findViewById(R.id.textView2);
	 	Button b4 = (Button) findViewById(R.id.button4);
	 	if (Double.parseDouble(tv1.getText().toString()) == 0.00) { // need to change condition to >= 100.00	
	 		b4.setText("Redeem Now!");
	 		b4.setClickable(true);
	 	}
	 	
	}
	
	
	// NullPointerException! 
	//	I don't know where to call this method!! (Not inside onCreate and not inside onClick())
	// to update the total spent
	private void updateAmt() {
		TextView tv = (TextView) findViewById(R.id.textView2);
		Double newAmt = getIntent().getExtras().getDouble("amount");
		Double amt = Double.parseDouble((String) tv.getText());
		amt += newAmt;
		tv.setText(String.valueOf(amt));
	}
	
}
