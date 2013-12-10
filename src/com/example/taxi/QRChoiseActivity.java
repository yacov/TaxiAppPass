package com.example.taxi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class QRChoiseActivity extends Activity {
	
	
	public Button btn_close;
	public Button btn_customer;
	public Button btn_taxi;

	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.qr_share_ch);
			
			btn_close = (Button) findViewById(R.id.btn_close);
			btn_customer = (Button) findViewById(R.id.but_customer);
			btn_taxi = (Button) findViewById(R.id.btn_taxi);
			
		    
			OnClickListener oclBtn = new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
		   			switch (v.getId()) 
		   			{
		   			case R.id.btn_close:
		   				finish();
		       			break;
		   			
		   			case R.id.but_customer:
		   				Intent intent1 = new Intent(QRChoiseActivity.this, QRActivity.class);
		       			startActivity(intent1);
		       			break;	
		       			
		   			case R.id.btn_taxi:
		   				Intent intent2 = new Intent(QRChoiseActivity.this, QRActivity.class);
		       			startActivity(intent2);
		       			break;	
		   			}
				}       
	   		
			};
		    
			btn_close.setOnClickListener(oclBtn);
			btn_customer.setOnClickListener(oclBtn);
			btn_taxi.setOnClickListener(oclBtn);  
		    
		}
		

}
