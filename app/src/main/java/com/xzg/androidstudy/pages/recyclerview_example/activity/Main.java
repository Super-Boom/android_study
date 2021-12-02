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
    }

}