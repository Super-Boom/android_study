package com.xzg.androidstudy.pages.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import com.xzg.androidstudy.R;
import com.xzg.androidstudy.broadcast.MyBroadcastReceiver;

public class BroadcastActivity extends AppCompatActivity {
    private MyBroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);


        // 注册广播接收器
        br = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.EXTRA_CAPTIVE_PORTAL);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");
        this.registerReceiver(br, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消广播接收器
        if (br != null) {
            unregisterReceiver(br);
        }
    }

}