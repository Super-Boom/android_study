package com.xzg.androidstudy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "BroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            // 接收到的是什么广播
            String action = intent.getAction();
            Log.d(TAG, "-----onReceive:" + action);
        }
    }
}
