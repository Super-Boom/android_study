package com.xzg.androidstudy.pages.recyclerview_example.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.xzg.androidstudy.R;
import com.xzg.androidstudy.adapter.ImageStageredAdapter;
import com.xzg.androidstudy.pages.recyclerview_example.adapter.RecyclerViewAdapter;
import com.xzg.xzglib.view.XzgRefreshView;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private XzgRefreshView refreshView;
    private ImageStageredAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        recyclerView = findViewById(R.id.recycler_view);
        // 自定义 ImageStageredAdapter 瀑布流中的图片高度
        adapter = new ImageStageredAdapter(this);
        recyclerView.setAdapter(adapter);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        // 设置分割线

    }

}