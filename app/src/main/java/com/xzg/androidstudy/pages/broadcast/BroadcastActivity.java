package com.xzg.androidstudy.pages.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import com.xzg.androidstudy.R;
import com.xzg.androidstudy.broadcast.BroadcastExample;

public class BroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("-----","dsajdsaj");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);


        BroadcastReceiver br = new BroadcastExample();
        IntentFilter filter = new IntentFilter(ConnectivityManager.EXTRA_CAPTIVE_PORTAL);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        this.registerReceiver(br, filter);

    }
}