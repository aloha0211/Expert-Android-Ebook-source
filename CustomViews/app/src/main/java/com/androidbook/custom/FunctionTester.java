package com.androidbook.custom;

import android.content.Context;
import android.content.Intent;

public class FunctionTester extends BaseTester 
{
	private static String tag = "Provider Tester";
	public FunctionTester(Context ctx, IReportBack target)
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
	public void invokeCustomView(Context ctx)
	{
		Intent i = new Intent(ctx,CustomViewActivity1.class);
		ctx.startActivity(i);		
	}
}
