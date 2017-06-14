package com.androidbook.forms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/*
 * A test form to demonstrate field validation
 */
public class SignupActivityTestForm 
extends FormActivity 
{
	ProgressDialog pd;
	private static String tag = "SignupActivity"; 
	
	//Form Fields
	EditText userid;
	EditText password1;
	EditText password2;
	EditText email;
	
	public SignupActivityTestForm()	{
		super(tag);
	}
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
	}
	//from form fields
	@Override
	protected void initializeFormFields() 
	{
		this.reportBack("form initialized");
		userid = (EditText)findViewById(R.id.userid);
		password1 = (EditText)findViewById(R.id.password1);
		password2 = (EditText)findViewById(R.id.password2);
		email = (EditText)findViewById(R.id.email);
		
		//Setup the validators
		addValidator(new Field(userid));
		addValidator(new Field(password1));
		addValidator(new Field(password2));
		addValidator(new Field(email));
		addValidator(new PasswordFieldRule(password1,password2));
	}
	
	private String getUserId()	{
		return getStringValue(R.id.userid);
	}
	private String getUserEmail()	{
		return getStringValue(R.id.email);
	}
	private String getPassword1()	{
		return getStringValue(R.id.password1);
	}
	private String getStringValue(int controlId)
	{
		TextView tv = (TextView)findViewById(controlId);
		if (tv == null)
		{
			throw new RuntimeException("Sorry Can't find the control id");
		}
		//view available
		return tv.getText().toString();
	}
	public void signupButtonClick(View v)
	{
		if (validateForm() == false)
		{
			reportTransient("Make sure all fields have valid values");
			return;
		}
		//everything is good
		String userid = getUserId();
		String password = getPassword1();
		String email = getUserEmail();
		reportTransient("Going to sign up now");
		signup(userid, email, password);
	}
	private void signup(String userid, String email, String password)	
	{
		gotoActivity(WelcomeActivity.class);
	}//signup-method
}//eof-class