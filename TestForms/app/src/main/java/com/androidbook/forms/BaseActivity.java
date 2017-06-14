package com.androidbook.forms;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/*
 * Provides many utility methods that are used by 
 * inherited classes. The utility methods include
 * such things as toasts, alerts, log messages,
 * and navigating to other activities. Also includes
 * the ability to turn on/off progress dialogs.  
 */
public abstract class BaseActivity extends Activity
{
    //private variables set by constructor
	
	//Uses the tag from derived classes
	private static String tag=null;
	
	//To turn/off progress dialogs
	private ProgressDialog pd = null;
	
	//Transfer the tag from derived classes
	public BaseActivity(String inTag)	{
		tag = inTag;
	}
	
	//Just a way to log a message
	public void reportBack(String message)	{
		reportBack(tag,message);
	}
	public void reportBack(String tag, String message)	{
		Log.d(tag,message);
	}
	
	//report a transient message and log it
	public void reportTransient(String message)	{
		reportTransient(tag,message);
	}
	//Report it using a toast
	public void reportTransient(String tag, String message)
	{
		String s = tag + ":" + message;
		Toast mToast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
		mToast.show();
		reportBack(tag,message);
		Log.d(tag,message);
	}
	
	//we often need to do string validations
	public boolean invalidString(String s)   {
    	return StringUtils.invalidString(s);
    }
    public boolean validString(String s)    {
    	return StringUtils.validString(s);
    }	
    //we often need to transfer to other activities
	public void gotoActivity(Class activityClassReference)
	{
		Intent i = new Intent(this,activityClassReference);
		startActivity(i);
	}

	//On callbacks turn on/off progress bars
	public void turnOnProgressDialog(String title, String message)	{
		pd = ProgressDialog.show(this,title,message);
	}
	public void turnOffProgressDialog()	{
		pd.cancel();
	}
	
	//Sometimes you need an explicit alert
	public void alert(String title, String message)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		   public void onClick(DialogInterface dialog, int which) {
		   }
		});
		alertDialog.show();		
	}
}//eof-class