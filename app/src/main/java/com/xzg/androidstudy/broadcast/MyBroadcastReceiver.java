package com.xzg.androidstudy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.xzg.androidstudy.pages.broadcast.BroadcastActivity;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "BroadcastReceiver";

    private TextView mTextView;

    public MyBroadcastReceiver() {

    }

    public MyBroadcastReceiver(TextView textView) {
        Log.d("-----广播接收器运行", "广播接收器运行");
        mTextView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            // 接收到的是什么广播
            String action = intent.getAction();
            Log.d(TAG, "-----onReceive:" + action);
            if (TextUtils.equals(action, BroadcastActivity.MY_ACTION)) {
                String content = intent.getStringExtra(BroadcastActivity.BROADCAST_CONTENT);
                if (mTextView != null) {
                    mTextView.setText(content);
                }
            }
        }
    }
}
