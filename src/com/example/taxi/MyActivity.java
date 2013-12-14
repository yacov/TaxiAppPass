package com.example.taxi;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.auth.GoogleAuthUtil;


public class MyActivity extends Activity {

    public AccountManager mAccountManager;
    public String[] mNamesArray;
    public String mEmail;
    public String mPhoneNumber;
    public TextView mOut;
    public Button btn_mshare;
    public ImageButton btn_go;
    public int mPoints = 0;
    SharedPreferences phonePref;
    public final String SAVED_PHONE = "saved_phone";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn_mshare = (Button) findViewById(R.id.btn_mshare);
        btn_go = (ImageButton) findViewById(R.id.imgbtn_go);
        btn_go.setBackgroundColor(Color.TRANSPARENT);
        mOut = (TextView) findViewById(R.id.message);
        ///////////// this will be function calling number of points from server
        mPoints = 10;

        mNamesArray = getAccountNames();
        if (mNamesArray.length > 0)
            mOut.setText("Hello " + mNamesArray[0].substring(1, mNamesArray[0].indexOf("@")) + "\n You have " + mPoints + " points");
        else
            mOut.setText("Please enter your Email:");

        //substring(1, example.indexOf("@"))


        OnClickListener oclBtn = new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                switch (v.getId()) {
                    case R.id.btn_mshare:
                        Intent intent = new Intent(MyActivity.this, QRChoiseActivity.class);
                        startActivity(intent);

                        break;

                    case R.id.imgbtn_go:
                        Intent intent2 = new Intent(MyActivity.this, MapActivity.class);
                        startActivity(intent2);
                        break;
                }
            }

        };

        btn_mshare.setOnClickListener(oclBtn);
        btn_go.setOnClickListener(oclBtn);

    }

    public String[] getAccountNames() {
        mAccountManager = AccountManager.get(this);
        Account[] accounts = mAccountManager.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        String[] names = new String[accounts.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = accounts[i].name;
        }
        return names;
    }

    public String loadPhone() {
        phonePref = getSharedPreferences("Phone_preference", MODE_PRIVATE);
        return phonePref.getString(SAVED_PHONE, "");
    }

/*	  public void savePhone(String phn) {
            phonePref = getSharedPreferences("Phone_preference",MODE_PRIVATE);
		    Editor ed = phonePref.edit();
		    ed.putString(SAVED_PHONE, phn);
		    ed.commit();
		  }
*/
}
