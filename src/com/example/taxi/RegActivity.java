package com.example.taxi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RegActivity extends Activity {
	 public Button btnSendSMS;
	 public Button btnCompare;
	 public Button btnRshare;
	 EditText txtPhoneNo;
	 public String phoneNo;
	 public TextView PhoneMes;
	 public TextView ConfMes;
	 int Conf_code;
		SharedPreferences phonePref;
		public final String SAVED_PHONE = "saved_phone";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registr);
		
		btnSendSMS = (Button) findViewById(R.id.btn_continue);
		btnCompare = (Button) findViewById(R.id.btn_confirm); btnCompare.setVisibility(View.INVISIBLE);
        txtPhoneNo = (EditText) findViewById(R.id.editTextphone);
        PhoneMes = (TextView) findViewById(R.id.phn_message); 
        ConfMes = (TextView) findViewById(R.id.cnf_message); ConfMes.setVisibility(View.INVISIBLE);
        btnRshare  = (Button) findViewById(R.id.btn_rshare);
        
        Random r = new Random(); 
        Conf_code = r.nextInt(8999)+1000;
        
        btnSendSMS.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {                
                String phoneNo = txtPhoneNo.getText().toString();                               
                if (phoneNo.length()>0)                
                    sendSMS(phoneNo);                
                else
                    Toast.makeText(getBaseContext(), 
                        "Please enter phone number:", 
                        Toast.LENGTH_LONG).show();
            }
        });  
        
        
		OnClickListener oclBtn = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	   			switch (v.getId()) 
	   			{
	   			case R.id.btn_rshare:
	   				Intent intent = new Intent(RegActivity.this, QRChoiseActivity.class);
	       			startActivity(intent);
	   			break;
	   			
	   			
	   			
	   			case R.id.btn_continue:
	   				
	   				phoneNo = txtPhoneNo.getText().toString();      
	   				
	                if (phoneNo.length() > 7) {               
	                    sendSMS(phoneNo); 
	                    
		   				btnSendSMS.setVisibility(View.INVISIBLE); PhoneMes.setVisibility(View.INVISIBLE);
		   				btnCompare.setVisibility(View.VISIBLE); ConfMes.setVisibility(View.VISIBLE);
		   				txtPhoneNo.setText("");

	                    
	                }
	                else {
	                    Toast.makeText(getBaseContext(), 
	                        "Please enter valid phone number.", 
	                        Toast.LENGTH_LONG).show();
	                    	txtPhoneNo.setText("");
	                }
	   				
	   			break;
	   			
	   			case R.id.btn_confirm:
	   				
	   				String codeChek = txtPhoneNo.getText().toString();                               
	                if (codeChek.length() > 0)
	                {
	             	   if(Integer.parseInt(codeChek) == Conf_code)
	             	   {
	             		savePhone(phoneNo);
	   	   				Intent intent2 = new Intent(RegActivity.this, MyActivity.class);
		       			startActivity(intent2);
	             	   }
	             	   else
	             	   {
	             		   Toast.makeText(getBaseContext(), 
	                                "Wrong code!", 
	                                Toast.LENGTH_SHORT).show();
	             		   txtPhoneNo.setText("");
	             	   }
	                }
	                                    
	                else {
	                    Toast.makeText(getBaseContext(), 
	                        "Please enter valid confirmation code.", 
	                        Toast.LENGTH_SHORT).show();
	                }


	       			break;	
	   			}
			}       
   		
		};
	    
		btnSendSMS.setOnClickListener(oclBtn);
		btnCompare.setOnClickListener(oclBtn);
		btnRshare.setOnClickListener(oclBtn);
        
	}


	
	private void sendSMS(String phoneNumber)
    {     		
		String message = Integer.toString(Conf_code);
            
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);        
    }    
	
	  public void savePhone(String phn) {
		    phonePref = getSharedPreferences("Phone_preference",MODE_PRIVATE);
		    Editor ed = phonePref.edit();
		    ed.putString(SAVED_PHONE, phn);
		    ed.commit();
		  }


}
