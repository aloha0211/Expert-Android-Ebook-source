package com.androidbook.testjson;

import com.androidbook.testjson.R;

import android.util.Log;
import android.view.MenuItem;

public class MainTestActivity extends MonitoredDebugActivity
implements IReportBack
{
	public static final String tag="MainTestActivity";
	GSONFunctionTester ft = null;
	
	public MainTestActivity()
	{
		super(R.menu.main_menu,tag);
		ft = new GSONFunctionTester(this,this);
		this.retainState();
	}
    protected boolean onMenuItemSelected(MenuItem item)
    {
    	Log.d(tag,item.getTitle().toString());
    	if (item.getItemId() == R.id.menu_no_op)
    	{
    		return true;
    	}
    	if (item.getItemId() == R.id.menu_test_toast)
    	{
    		ft.testToast();
    		return true;
    	}
    	if (item.getItemId() == R.id.menu_test_json)
    	{
    		ft.testJSON();
    		return true;
    	}
    	if (item.getItemId() == R.id.menu_test_get)
    	{
    		ft.retrieveJSON();
    		return true;
    	}
    	if (item.getItemId() == R.id.menu_test_store)
    	{
    		ft.storeJSON();
    		return true;
    	}
    	if (item.getItemId() == R.id.menu_test_preferences)
    	{
    		ft.testEscapeCharactersInPreferences();
    		return true;
    	}
    	if (item.getItemId() == R.id.menu_test_internal)
    	{
    		ft.saveJSONToPrivateStorage();
    		return true;
    	}
    	return true;
    }
}