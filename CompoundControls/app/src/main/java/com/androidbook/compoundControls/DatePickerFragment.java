package com.androidbook.compoundControls;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment
	implements DatePickerDialog.OnDateSetListener 
{
	public static String tag = "DatePickerFragment";
	private DurationControl parent;
	private int buttonId;
	
	public DatePickerFragment(DurationControl inParent, int inButtonId)
	{
		parent = inParent;
		buttonId = inButtonId;
		Bundle argsBundle = this.getArguments();
		if (argsBundle == null)
		{
			argsBundle = new Bundle();
		}
		argsBundle.putInt("parentid", inParent.getId());
		argsBundle.putInt("buttonid", buttonId);
		this.setArguments(argsBundle);
	}
	
	//Default constructor
	public DatePickerFragment()
	{
		Log.d(tag,"constructed");
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) 
	{
		//this.establishParent();
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
	    // Do something with the date chosen by the user
		parent.onDateSet(buttonId, year, month, day);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.d(tag,"DatePickerFragment onActivity created called");
		this.establishParent();
	}
	
	private void establishParent()
	{
		if (parent != null) return;
		Log.d(tag, "establishing parent");
		int parentid = this.getArguments().getInt("parentid");
		buttonId =  this.getArguments().getInt("buttonid");
		View x = this.getActivity().findViewById(parentid);
		if (x == null)
		{
			throw new RuntimeException("Sorry not able to establish parent on restart");
		}
		parent = (DurationControl)x;
	}
}