package com.xzg.androidstudy.pages.fragment_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xzg.androidstudy.R;
import com.xzg.androidstudy.event_bus.EventBusExample;

import org.greenrobot.eventbus.EventBus;

public class FragmentCreate extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_create);


        // 加载fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentItem fragmentItem = new FragmentItem();
        fragmentTransaction.add(R.id.fragmentItem, fragmentItem).commit();

        // 添加点击事件
        Button sendEventBtn = findViewById(R.id.sendEvent);
        sendEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.sendEvent) {
                    // 发送event消息
                    EventBus.getDefault().post(new EventBusExample("我接收到事件了"));
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}