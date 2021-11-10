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
        textView.setText("hahahah");
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMsg(EventBusExample eventBusExample) {
        textView.setText(eventBusExample.msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 注销event
        EventBus.getDefault().unregister(this);
    }
}