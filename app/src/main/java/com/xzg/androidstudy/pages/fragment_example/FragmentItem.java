package com.xzg.androidstudy.pages.fragment_example;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xzg.androidstudy.R;
import com.xzg.androidstudy.event_bus.EventBusExample;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class FragmentItem extends Fragment {


    TextView textView;
    TextView textView2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // 注册event
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        textView = view.findViewById(R.id.fragment_get_event_msg);
        textView2 = view.findViewById(R.id.fragment_get_event_msg2);
        return view;
    }

    /**
     * 订阅者1
     * @param eventBusExample
     */
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onGetMsg(EventBusExample eventBusExample) {
        textView.setText(eventBusExample.msg);
    }

    /**
     * 订阅者2
     * @param eventBusExample
     */
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onGetMsg2(EventBusExample eventBusExample) {
        textView2.setText(eventBusExample.msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 注销event
        EventBus.getDefault().unregister(this);
    }
}