package com.androidbook.custom;

import android.app.Activity;
import android.os.Bundle;

public class CustomViewActivity1 
extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CircleView(this));
    }
}//eof-activity