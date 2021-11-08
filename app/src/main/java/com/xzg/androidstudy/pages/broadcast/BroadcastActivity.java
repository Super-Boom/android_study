package com.xzg.androidstudy.pages.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xzg.androidstudy.R;
import com.xzg.androidstudy.broadcast.MyBroadcastReceiver;

public class BroadcastActivity extends AppCompatActivity {
    private MyBroadcastReceiver myBroadcastReceiver;
    public static String MY_ACTION = "XZG1";
    public static String BROADCAST_CONTENT = "broadcast_content";

    private EditText editText;
    private Button sendBroadcastMsgBtn;
    private TextView receiveTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        // 设置应用注页面标题
        setTitle(getPackageName());
        setContentView(R.layout.activity_broadcast);

        // 自定义广播
        editText = findViewById(R.id.broadcast_send_msg);
        sendBroadcastMsgBtn = findViewById(R.id.sendBroadcastBtn);
        receiveTextView = findViewById(R.id.broadcast_receive_msg);

        // 注册广播接收器
        myBroadcastReceiver = new MyBroadcastReceiver(receiveTextView);
        // 为广播接收器添加action
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
//        filter.addDataScheme("package");
        filter.addAction(MY_ACTION);


        // 注册广播接收器
        registerReceiver(myBroadcastReceiver, filter);

        // 发送广播
        sendBroadcastMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 新建广播
                Intent intent = new Intent();
                intent.setAction(MY_ACTION);
                // 放入广播要携带的数据
                intent.putExtra(BROADCAST_CONTENT, editText.getText().toString());
                sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消广播接收器
        if (myBroadcastReceiver != null) {
            unregisterReceiver(myBroadcastReceiver);
        }
    }

}