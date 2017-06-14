package com.androidbook.testjson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class GSONFunctionTester extends BaseTester 
{
	private static String tag = "GSONFunctionTester";
	public GSONFunctionTester(Context ctx, IReportBack target)
	{
		super(ctx, target);
	}
	public void testToast()
	{
		this.mReportTo.reportTransient(tag, "Test Toast Message");
		this.mReportTo.reportTransient(tag, "22: Test Toast Message");
		this.mReportTo.reportTransient(tag, "33: Test Toast Message");
		this.mReportTo.reportTransient(tag, "44: Test Toast Message");
	}
	public void testJSON()
	{
		MainObject mo = MainObject.createTestMainObject();
		Gson gson = new Gson();
		String jsonString = gson.toJson(mo);
		this.mReportTo.reportBack(tag, jsonString);
		MainObject mo1 = gson.fromJson(jsonString, MainObject.class);
		this.mReportTo.reportBack(tag, jsonString);
	}
	public void storeJSON()
	{
		MainObject mo = MainObject.createTestMainObject();
		//
		Gson gson = new Gson();
		String jsonString = gson.toJson(mo);
		this.mReportTo.reportBack(tag, jsonString);
		MainObject mo1 = gson.fromJson(jsonString, MainObject.class);
		this.mReportTo.reportBack(tag, jsonString);
		
		SharedPreferences sp = getSharedPreferences();
		SharedPreferences.Editor spe = sp.edit();
		spe.putString("json", jsonString);
		spe.commit();
	}
	
	public void saveJSONToPrivateStorage()
	{
		String json = createJSON();
		saveToInternalFile(json);
		String retrievedString = this.readFromInternalFile();
		
		//Create the object from retrievedString
		Gson gson = new Gson();
		MainObject mo = gson.fromJson(retrievedString, MainObject.class);
		//makesure it is the same object
		MainObject srcObject = MainObject.createTestMainObject();
		String compareResult = mo.checkTestMainObject(srcObject);
		mReportTo.reportBack(tag,compareResult);
	}
	private String createJSON()
	{
		MainObject mo = MainObject.createTestMainObject();
		Gson gson = new Gson();
		String jsonString = gson.toJson(mo);
		return jsonString;
	}
	public void retrieveJSON()
	{
		SharedPreferences sp = getSharedPreferences();
		String jsonString = sp.getString("json", null);
		if (jsonString == null)
		{
			mReportTo.reportBack(tag,"Not able to read the preference");
			return;
		}
		Gson gson = new Gson();
		MainObject mo = gson.fromJson(jsonString, MainObject.class);
		mReportTo.reportBack(tag,"Object successfully retrieved");
		String compareResult = MainObject.checkTestMainObject(mo);
		mReportTo.reportBack(tag,compareResult);
		return;
	}
	
	public void testEscapeCharactersInPreferences()
	{
		String testString = "<node1>blabhhh</ndoe1>";
		SharedPreferences sp = getSharedPreferences();
		SharedPreferences.Editor spe = sp.edit();
		spe.putString("test", testString);
		spe.commit();
		String savedString = sp.getString("test", null);
		if (savedString == null)
		{
			mReportTo.reportBack(tag,"no saved string");
			return;
		}
		//savedstring exists
		mReportTo.reportBack(tag,savedString);
		if (testString.equals(savedString))
		{
			mReportTo.reportBack(tag,"Saved the string properly. Match");
			return;
		}
		//they dont match
		mReportTo.reportBack(tag,"They don't match");
		return;
	}
	private SharedPreferences getSharedPreferences()
	{
		SharedPreferences sp 
		= MyApplication.s_applicationContext.getSharedPreferences("myprefs", Context.MODE_PRIVATE);
		return sp;
	}
	
	
	private String readFromInternalFile()
	{
		FileInputStream fis = null;
		try {
			Context appContext = MyApplication.s_applicationContext; 
			fis = appContext.openFileInput("datastore-json.txt");
			String jsonString = readStreamAsString(fis);
			return jsonString;
		}
		catch(IOException x)
		{
			mReportTo.reportBack(tag,"Cannot create or write to file");
			return null;
		}
		finally
		{
			closeStreamSilently(fis);
		}
	}
	private void saveToInternalFile(String ins)
	{
		
		FileOutputStream fos = null;
		try {
			Context appContext = MyApplication.s_applicationContext; 
			fos = appContext.openFileOutput("datastore-json.txt"
					                ,Context.MODE_PRIVATE);
			fos.write(ins.getBytes());
		}
		catch(IOException x)
		{
			mReportTo.reportBack(tag,"Cannot create or write to file");
		}
		finally
		{
			closeStreamSilently(fos);
		}
	}
	private void copy(InputStream reader, OutputStream writer)
    throws IOException
    {
      byte byteArray[] = new byte[4092];
       while(true)
       {
          int numOfBytesRead = reader.read(byteArray,0,4092);
          if (numOfBytesRead == -1)
          {
             break;
          }
          // else
          writer.write(byteArray,0,numOfBytesRead);
       }
       return;
    }	
	private String readStreamAsString(InputStream is)
    throws FileNotFoundException, IOException
	 {
	
	    ByteArrayOutputStream baos = null;
	    try
	    {
	       baos = new ByteArrayOutputStream();
	       copy(is,baos);
	       return baos.toString();
	    }
	    finally
	    {
	       if (baos != null)
	    	  closeStreamSilently(baos);
	    }
	 }	
	private void closeStreamSilently(OutputStream os)
	{
		if (os == null) return;
		//os is not null
		try {os.close();} catch(IOException x)
		{
			throw new RuntimeException("This shouldn't happen. exception closing a file",x);
		}
	}
	private void closeStreamSilently(InputStream os)
	{
		if (os == null) return;
		//os is not null
		try {os.close();} catch(IOException x)
		{
			throw new RuntimeException("This shouldn't happen. exception closing a file",x);
		}
	}
}//eof-class
