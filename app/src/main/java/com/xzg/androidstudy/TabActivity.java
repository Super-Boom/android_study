package com.xzg.androidstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.flyco.tablayout.SlidingTabLayout;

public class TabActivity extends AppCompatActivity {


    SlidingTabLayout slidingTabLayout;
    ViewPager viewPager;
    private String[] title = {"陆飞", "索隆", "山治", "娜美", "乌索普", "乔巴"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        // tab的操作
        initSlidingTabLayout();
        // 找到tabLayout
        // 动态添加内容
        // 设置切换效果
    }

    // 初始化layout组件
    private void initSlidingTabLayout() {
        slidingTabLayout.setViewPager(viewPager, title);
    }
}