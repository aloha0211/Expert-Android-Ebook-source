package com.androidbook.telephony;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

public class MyBReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
    	String action = intent.getAction();
    	Log.d(TelephonyDemo.TAG, "Got action of " + action);
    	if(TelephonyDemo.SENT_ACTION.compareTo(action) == 0) {
	        Log.d(TelephonyDemo.TAG, "SMS sent intent received.");
	        switch(getResultCode()) {
	        case Activity.RESULT_OK:
		        Log.d(TelephonyDemo.TAG, "SMS sent OK.");
	        	break;
	        case SmsManager.RESULT_ERROR_RADIO_OFF:
		        Log.d(TelephonyDemo.TAG, "*** SMS not sent. Radio is off.");
	        	break;
	        case SmsManager.RESULT_ERROR_NO_SERVICE:
		        Log.d(TelephonyDemo.TAG, "*** SMS not sent. No SMS service.");
	        	break;
	        case SmsManager.RESULT_ERROR_NULL_PDU:
		        Log.d(TelephonyDemo.TAG, "*** SMS not sent. PDU was null.");
	        	break;
	        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
		        Log.d(TelephonyDemo.TAG, 
		        		"*** SMS not sent. Unknown failure.");
	        	break;
	        default:
		        Log.d(TelephonyDemo.TAG, "*** Unknown sent code: "
		        		+ getResultCode());
	        	break;
	        }
    	}
    	if(TelephonyDemo.DELIVERED_ACTION.compareTo(action) == 0) {
	        Log.d(TelephonyDemo.TAG, "SMS delivered intent received.");
	        switch(getResultCode()) {
	        case Activity.RESULT_OK:
		        Log.d(TelephonyDemo.TAG, "SMS delivered.");
	        	break;
	        case Activity.RESULT_CANCELED:
		        Log.d(TelephonyDemo.TAG, "*** SMS not delivered.");
	        	break;
	        default:
		        Log.d(TelephonyDemo.TAG, "*** Unknown delivery code: "
		        		+ getResultCode());
	        	break;
	        }
    	}
    }
}
