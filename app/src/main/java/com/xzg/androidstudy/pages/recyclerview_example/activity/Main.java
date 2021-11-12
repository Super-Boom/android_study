package com.xzg.androidstudy.pages.recyclerview_example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.xzg.androidstudy.R;
import com.xzg.androidstudy.pages.recyclerview_example.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        recyclerView = findViewById(R.id.recycler_view);
        // 线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(this);
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
    }


}