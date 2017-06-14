package com.androidbook.custom;

import com.androidbook.custom.R;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class TestCustomViewActivity extends MonitoredDebugActivity
implements IReportBack
{
	public static final String tag="HelloWorld";
	FunctionTester ft = null;
	
	public TestCustomViewActivity()
	{
		super(R.menu.main_menu,tag);
		ft = new FunctionTester(this,this);
		this.retainState();
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_custom_view_activity_layout);
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
    	if (item.getItemId() == R.id.menu_cview)
    	{
    		ft.invokeCustomView(this);
    		return true;
    	}
    	return true;
    }
}