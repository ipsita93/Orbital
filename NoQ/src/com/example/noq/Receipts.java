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
	
	private static final String Integer = null; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receipts);
		
		// clicking on receipt1 button 
		Button b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Receipts.this, Main.class);
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
		
		/* has errors!!
		// to update the total spent
		TextView tv = (TextView) findViewById(R.id.textView2);
		double amt = Double.parseDouble((String) tv.getText());
		amt += Double.parseDouble(getIntent().getExtras().getString("amount"));
		*/
		
	}
}
