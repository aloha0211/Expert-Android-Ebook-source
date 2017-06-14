package com.androidbook.telephony;

import java.util.ArrayList;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TelephonyDemo extends Activity
{
    protected static final String TAG = "TelephonyDemo";
    protected static final String SENT_ACTION =
    	"com.androidbook.telephony.SMS_SENT_ACTION";
    protected static final String DELIVERED_ACTION =
    	"com.androidbook.telephony.SMS_DELIVERED_ACTION";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	}

	public void doSend(View view) {
        EditText addrTxt = 
                (EditText)findViewById(R.id.addrEditText);

        EditText msgTxt = 
                (EditText)findViewById(R.id.msgEditText);

        try {
            sendSmsMessage(
               	addrTxt.getText().toString(),
                msgTxt.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS", 
                	Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void sendSmsMessage(String address, String message)
    		throws Exception
    {
        SmsManager smsMgr = SmsManager.getDefault();
        // Split the message up into manageable chunks if needed
        ArrayList<String> messages = smsMgr.divideMessage(message);

        if(messages.size() > 1) {
        	int count = messages.size();
        	
        	// Will need to send with multipart,
        	// so prepare the pending intents
        	ArrayList<PendingIntent> sentPIs = 
        		new ArrayList<PendingIntent>(count);
        	
        	ArrayList<PendingIntent> deliveredPIs = 
        		new ArrayList<PendingIntent>(count);
        	
        	for(int i = 0; i<count; i++) {
        		sentPIs.add(PendingIntent.getBroadcast(
            		TelephonyDemo.this, 0, 
            		new Intent(SENT_ACTION), 0));
        		deliveredPIs.add(PendingIntent.getBroadcast(
            		TelephonyDemo.this, 0, 
            		new Intent(DELIVERED_ACTION), 0));
        	}
            smsMgr.sendMultipartTextMessage(address, null,
            		messages, sentPIs, deliveredPIs);
            Toast.makeText(this, "Multipart SMS Sent", 
                	Toast.LENGTH_LONG).show();
        }
        else {
            smsMgr.sendTextMessage(address, null, message, 
            		PendingIntent.getBroadcast(
            			TelephonyDemo.this, 0,
            			new Intent(SENT_ACTION), 0),
                	PendingIntent.getBroadcast(
                		TelephonyDemo.this, 0,
                    	new Intent(DELIVERED_ACTION), 0)
                    );
            Toast.makeText(this, "SMS Sent", 
                	Toast.LENGTH_LONG).show();
        }
    }
}
