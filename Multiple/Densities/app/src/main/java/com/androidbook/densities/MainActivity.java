package com.androidbook.densities;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    private static final String TAG = "Densities";

    // This application was created to play around with several of the concepts
    // of chapter 5 (Expert Android by Satya Komatineni and Dave MacLean.
    // For example, notice the different layout resource directories using
    // the -w<N>dp qualifier. When tested on a tablet in portrait and landscape
    // mode, you will see the different layouts selected because of the
    // available width of the screen.
    // Within this activity class, notice the logging of the device's Android
    // API level and the width setting (in pixels). Feel free to explore the
    // other members of the DisplayMetrics class.
    // Look at the AndroidManifest.xml file to see the use of <uses-feature>
    // and <uses-permission>. It is better to attempt installing this application
    // onto a device that does not have NFC capabilities, since then you'll see
    // that the permissions are still requested even though the feature is not
    // there. Instead of deploying using Eclipse, take the .apk file, push it to
    // the SDCARD of your emulator or device, then use a file manager to select
    // it. This will launch the APK Manager which will take over and do the
    // install of the application. The only way to see the behavior of Google
    // Play is to actually deploy an application to Google Play.

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.d(TAG, "Device version is " + Build.VERSION.SDK_INT);
        Log.d(TAG, "Width is " + getResources().getDisplayMetrics().widthPixels);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
