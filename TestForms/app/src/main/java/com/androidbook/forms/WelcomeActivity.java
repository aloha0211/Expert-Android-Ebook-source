package com.androidbook.forms;

import android.os.Bundle;

public class WelcomeActivity 
extends BaseActivity 
{
	public WelcomeActivity() 
	{
		super("WelcomeActivity");
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
	}

}//eof-class