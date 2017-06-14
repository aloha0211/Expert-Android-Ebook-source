// This file is PhoneCallActivity.java
package com.androidbook.phonecall.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

public class PhoneCallActivity extends Activity {
    private static final String TAG = "PhoneCallDemo";
    private TelephonyManager teleMgr = null;
    private MyPhoneStateListener myListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        teleMgr = 
                (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        myListener = new MyPhoneStateListener();
        teleMgr.listen(myListener, PhoneStateListener.LISTEN_CALL_STATE);
        
        String myIMEI = teleMgr.getDeviceId();
        Log.d(TAG, "device IMEI is " + myIMEI);

        if (teleMgr.getSimState() == TelephonyManager.SIM_STATE_READY) {
        	String country = teleMgr.getSimCountryIso();
        	Log.d(TAG, "SIM country ISO is " + country);
        }
    }

    @Override
    public void onResume() {
    	super.onResume();
    	Log.d(TAG, "In onResume");
    }

    @Override
    public void onPause() {
    	super.onPause();
    	Log.d(TAG, "In onPause");
    }

    // Only unregister the listener if this app is going away.
    // Otherwise, when the user tries to make or receive a phone
    // call, this app will get paused and we don't want to stop
    // listening when we're put into the background.
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	Log.d(TAG, "In onDestroy");
        teleMgr.listen(myListener, PhoneStateListener.LISTEN_NONE);
    }
    
    public void doClick(View target) {
    	switch(target.getId()) {
    	case R.id.callBtn:
        	Intent intent = new Intent(Intent.ACTION_VIEW,
        		Uri.parse("tel:5551212"));
        	startActivity(intent);
        	break;
    	case R.id.quitBtn:
    		finish();
        	break;
        default:
        	break;
    	}
    }
    
    public class MyPhoneStateListener extends PhoneStateListener
    {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            switch(state)
            {
                case TelephonyManager.CALL_STATE_IDLE:
                	Log.d(TAG, "call state idle...incoming number is ["+
                        incomingNumber+"]");
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                	Log.d(TAG, "call state ringing...incoming number is ["+
                        incomingNumber+"]");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                	Log.d(TAG, "call state offhook...incoming number is ["+
                        incomingNumber+"]");
                    break;
                default:
                	Log.d(TAG, "call state ["+state+"]...incoming number is ["+
                        incomingNumber+"]");
                    break;
            }
        }
    }
}
