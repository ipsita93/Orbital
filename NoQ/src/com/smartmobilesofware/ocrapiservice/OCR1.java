package com.smartmobilesofware.ocrapiservice;

import com.example.noq.R;
import com.example.noq.Receipt1;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OCR1 extends Activity implements OnClickListener {
	private final int RESPONSE_OK = 200;
	private final int IMAGE_PICKER_REQUEST = 1;
	private static final int CAMERA_REQUEST = 1888; 
    private ImageView imageView;
	private TextView picNameText;
	
	private String apiKey;
	private String langCode;
	private String fileName;
	
   	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.ocr);
		
		picNameText = (TextView) findViewById(R.id.imageName);
		apiKey = "QdgJmSnWjK";
		langCode = "en";
		this.imageView = (ImageView)this.findViewById(R.id.chosenImage);
		ImageView crossButton = (ImageView) findViewById(R.id.crossButton);
		
		crossButton.setClickable(false);
		crossButton.setEnabled(false);
		crossButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				imageView.setImageResource(R.drawable.icon);
				picNameText.setText("Selected: ");
				fileName = null;
				apiKey.equals("");
				langCode.equals("");
			}
		});
		
	    Button captureButton = (Button) this.findViewById(R.id.captureImageButton);
	    captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
                startActivityForResult(cameraIntent, CAMERA_REQUEST); 
            }
        });
	    
	   	final Button pickButton = (Button) findViewById(R.id.pickImagebutton);
		pickButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Starting image picker activity
				startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI), IMAGE_PICKER_REQUEST);
			}
		});

		final Button convertButton = (Button) findViewById(R.id.convert);
		convertButton.setOnClickListener(this);
	}
	
	//Override of hardware back button
	@Override 
	public void onBackPressed(){
		Intent returnIntent = new Intent(OCR1.this, Receipt1.class);
		returnIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
		returnIntent.putExtra("ocrDone", false);
		// bring an existing instance of the called activity type present in the current stack to the 
		// foreground instead of creating a new instance
		// setResult(RESULT_OK,returnIntent);   
		startActivity(returnIntent);
	} 
	
	@Override
	public void onClick(View v) {
		// apiKey = apiKeyField.getText().toString();
		// langCode = langCodeField.getText().toString();
		
		// Checking are all fields set
		if (fileName != null && !apiKey.equals("") && !langCode.equals("")) {
			final ProgressDialog dialog = ProgressDialog.show( OCR1.this, "Loading ...", "Converting to text ... (may take up to one minute)", true, false);
			final Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					final OCRServiceAPI apiClient = new OCRServiceAPI(apiKey);
					apiClient.convertToText(langCode, fileName);
					
					// Doing UI related code in UI thread
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							dialog.dismiss();
							
							// Showing response dialog
							final AlertDialog.Builder alert = new AlertDialog.Builder(OCR1.this);
							// alert.setMessage(apiClient.getResponseText());
							alert.setPositiveButton(
								"OK",
								new DialogInterface.OnClickListener() {
									public void onClick( DialogInterface dialog, int id) {
										Intent goBack = new Intent(OCR1.this, Receipt1.class);
										goBack.putExtra("ocrDone", true);
										goBack.putExtra("text", apiClient.getResponseText());
										goBack.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
										setResult(RESULT_OK, goBack);
										startActivity(goBack);
									}
								});
							
							// Setting dialog title related from response code
							if (apiClient.getResponseCode() == RESPONSE_OK) {
								alert.setTitle("Success");
								alert.setMessage(apiClient.getResponseText());
							} 
							else {
								alert.setTitle("Failed");
							}
							alert.show();
						}
					});
				}
			});
			thread.start();
		} else {
			Toast.makeText(OCR1.this, "All data are required.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		ImageView crossButton = (ImageView) findViewById(R.id.crossButton);
		
		if (requestCode == IMAGE_PICKER_REQUEST && resultCode == RESULT_OK) {
			Uri selectedImageUri = data.getData();
			fileName = getRealPathFromURI(selectedImageUri);
			picNameText.setText("Selected: en" + getStringNameFromRealPath(fileName));
			imageView.setImageURI(selectedImageUri);
			crossButton.setClickable(true);
			crossButton.setEnabled(true);
		}
		
	    if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
	    	fileName = getRealPathFromURI(data.getData());
			picNameText.setText("Selected: en" + getStringNameFromRealPath(fileName));
			
            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
            imageView.setImageBitmap(photo);
        	crossButton.setClickable(true);
			crossButton.setEnabled(true);
	    }  
	}

	/*
	 * Returns image real path.
	 */
	private String getRealPathFromURI(final Uri contentUri) {
		final String[] proj = { MediaStore.Images.Media.DATA };
		final Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		
		return cursor.getString(column_index);
	}
    
	/*
	 * Cuts selected file name from real path to show in screen.
	 */
	private String getStringNameFromRealPath(final String bucketName) {
		return bucketName.lastIndexOf('/') > 0 ? bucketName.substring(bucketName.lastIndexOf('/') + 1) : bucketName;
	}
}

