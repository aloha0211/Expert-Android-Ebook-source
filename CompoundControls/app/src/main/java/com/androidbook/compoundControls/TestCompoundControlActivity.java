package com.androidbook.compoundControls;

import android.util.Log;
import android.view.MenuItem;

public class TestCompoundControlActivity extends MonitoredDebugActivity
implements IReportBack
{
	public static final String tag="TestCompoundControlActivity";
	
	public TestCompoundControlActivity()
	{
		super(R.menu.main_menu,tag);
		this.retainState();
	}
    protected boolean onMenuItemSelected(MenuItem item)
    {
    	Log.d(tag,item.getTitle().toString());
    	if (item.getItemId() == R.id.menu_no_op)
    	{
    		return true;
    	}
    	return true;
    }
}//eof-class