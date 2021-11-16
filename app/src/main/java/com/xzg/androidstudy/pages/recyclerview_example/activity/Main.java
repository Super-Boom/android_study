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
import com.xzg.androidstudy.pages.recyclerview_example.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        recyclerView = findViewById(R.id.recycler_view);
        // 线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // 横向排列ItemView
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // 数据反向展示
        linearLayoutManager.setReverseLayout(true);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(this, recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);

        // 添加数据
        findViewById(R.id.add_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> data = new ArrayList<>();

                for (int i = 0; i < 20; i++) {
                    String s = "第" + i + "条数据";
                    data.add(s);
                }
                recyclerViewAdapter.setDataSource(data);
            }
        });

        // 修改布局
        findViewById(R.id.change_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 从线性布局切换为网格布局
                if (recyclerView.getLayoutManager().getClass() == LinearLayoutManager.class) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(v.getContext(), 2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                } else if (recyclerView.getLayoutManager().getClass() == GridLayoutManager.class) {
                    // 瀑布流布局
                    staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    // 解决滑动位置变化
                    staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                    recyclerView.setLayoutManager(staggeredGridLayoutManager);
                } else {
                    // 线性布局
                    recyclerView.setLayoutManager(linearLayoutManager);

                }
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                staggeredGridLayoutManager.invalidateSpanAssignments();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

}