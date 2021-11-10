package com.xzg.androidstudy.pages.event_bus_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.xzg.androidstudy.R;
import com.xzg.androidstudy.event_bus.EventBusExample;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Activity1 extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);

        textView = findViewById(R.id.eventBusTxt);
        // 注册EventBus
        EventBus.getDefault().register(this);
        // 创建子线程用event_bus发送事件
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    EventBus.getDefault().post(new EventBusExample("我接收到事件了"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 消息接收者
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBusExample event) {
        textView.setText(event.msg);
    }

    // 销毁Event
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}