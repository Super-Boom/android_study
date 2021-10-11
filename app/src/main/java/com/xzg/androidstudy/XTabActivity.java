package com.xzg.androidstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

public class XTabActivity extends AppCompatActivity {

    XTabLayout tab;

    ViewPager2 pagers;

    List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_tab);

        // 1.找到XTabLayout
        tab = findViewById(R.id.x_tab);
        // 2.动态添加内容(财经，军事，科技，视频，体育)
        // a.实例化导航块
        XTabLayout.Tab t1 = tab.newTab();
        // b.设置名字
        t1.setText("财经");
        // c.添加
        tab.addTab(t1);

        tab.addTab(tab.newTab().setText("军事"));
        tab.addTab(tab.newTab().setText("科技"));
        tab.addTab(tab.newTab().setText("视频"));
        tab.addTab(tab.newTab().setText("体育"));
        // 3.设置切换效果
        tab.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                // 获取当前导航卡的位置及文本
                int position = tab.getPosition();
                String text = tab.getText().toString();
                Toast.makeText(XTabActivity.this, position + "--" + text, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });

        pagers = findViewById(R.id.pagers);
        // 添加Fragment
        list.add(new F)
    }
}