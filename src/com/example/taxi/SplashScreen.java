package com.example.taxi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import java.io.File;

public class SplashScreen extends Activity {

    /** 
     * ����� ��� ��������� ��������� �������� 
     */  
    private Thread mSplashThread;      
  
      
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
     // ������ ��������  
        setContentView(R.layout.splash);  
          
        final SplashScreen sPlashScreen = this;     
          
        // ����� ��� �������� ������� ��������  
        mSplashThread =  new Thread(){  
            @Override  
            public void run(){  
                try {  
                    synchronized(this){  
                        // ���� ��������� �����, ��� ����� �� �������������  
                    wait(2000);  
                    }  
                }  
                catch(InterruptedException ex){                      
                }  
  
                finish();  
                  
                // ��������� �������� �����  
                Intent intent = new Intent(); 
                File f = new File("/data/data/com.example.taxi/shared_prefs/Phone_preference.xml");
                if (f.exists())
                {intent.setClass(sPlashScreen, MyActivity.class);}  
                else
                {intent.setClass(sPlashScreen, RegActivity.class);}
                startActivity(intent);  
                                    
            }  
        };  
          
        mSplashThread.start();          
    }  
          
    @Override  
    public boolean onTouchEvent(MotionEvent evt)  
    {  
        if(evt.getAction() == MotionEvent.ACTION_DOWN)  
        {  
            synchronized(mSplashThread){  
                mSplashThread.notifyAll();  
            }  
        }  
        return true;  
    }      
}

