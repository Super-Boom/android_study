package com.xzg.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BackDataToMain extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_data_to_main);

        Button mBtnClose = findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                // 把要返回的数据存放在intent
                intent.putExtra("second", "我是第二页的信息");
                // 设置返回数据
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}