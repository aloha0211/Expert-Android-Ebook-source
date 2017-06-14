package com.androidbook.testjson;

import java.util.ArrayList;

public class MainObject 
{
	public int intValue = 5;
	public String strinValue = "st<ri>\"ng\"Value<node1>test</node2>";
	public String[] stringArray;
	public ArrayList<ChildObject> childList = new ArrayList<ChildObject>();
	
	public void addChild(ChildObject co)
	{
		childList.add(co);
	}
	
	public void populateStringArray()
	{
		stringArray = new String[2];
		stringArray[0] = "first";
		stringArray[1] = "second";
	}
	public static MainObject createTestMainObject()
	{
		MainObject mo = new MainObject();
		mo.populateStringArray();
		mo.addChild(new ChildObject("Adam",30));
		mo.addChild(new ChildObject("Eve",28));
		return mo;
	}
	public static String checkTestMainObject(MainObject mo)
	{
		MainObject moCopy = createTestMainObject();
		if (!(mo.strinValue.equals(moCopy.strinValue)))
		{
			return "String values don't match:" + mo.strinValue;
		}
		if (mo.childList.size() != moCopy.childList.size())
		{
			return "array list size doesn't match";
		}
		//get first child
		ChildObject firstChild = mo.childList.get(0);
		ChildObject firstChildCopy = moCopy.childList.get(0);
		if (!firstChild.name.equals(firstChildCopy.name))
		{
			return "first child name doesnt match";
		}
		return "everything matches";
	}
	
}


