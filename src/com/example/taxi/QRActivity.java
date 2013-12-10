package com.example.taxi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class QRActivity extends Activity {	
	
	ImageView image;
    Button btn_share;
    Button btn_close;
	String qrData = "https://play.google.com/store/apps/details?id=com.skype.raider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_share);    
        
        btn_share = (Button) findViewById(R.id.but_share);
        btn_close = (Button) findViewById(R.id.btn_close);
        image = (ImageView) findViewById(R.id.imageView1);  
        
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int qrCodeDimention = displaymetrics.widthPixels;
        
       QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrData, null, BarcodeFormat.QR_CODE.toString(), qrCodeDimention);

        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            image.setImageBitmap(bitmap);
        } 
        catch (WriterException e) {
            e.printStackTrace();
        }
        
        
    	OnClickListener oclBtn = new OnClickListener() {

    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    	    	switch (v.getId()) 
    				{
    				case R.id.btn_close:
    					finish();

    				break;
    				
    				case R.id.but_share:
    			    	 Intent intent = new Intent(Intent.ACTION_SEND);
    					 intent.setType("text/plain");
    					 intent.putExtra(Intent.EXTRA_TEXT, qrData);
    					 intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download this fun application!");
    					 startActivity(Intent.createChooser(intent, "Share"));
    				
    	   			break;	
    				}
    		}       
    		
    	};
        
    	btn_share.setOnClickListener(oclBtn);
    	btn_close.setOnClickListener(oclBtn);
        
    }


}
