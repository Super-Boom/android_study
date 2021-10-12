package com.xzg.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpReqActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_req);

        final Button getBtn = findViewById(R.id.get);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(v);
            }
        });
    }

    public void getData(View v) {
        switch (v.getId()) {
            case R.id.get:
                new Thread() {
                    public void run() {
                        super.run();
                        get();
                    }
                }.start();
                break;
        }
    }

    private void get() {
        try {
            // HttpURLConnection
            // 1.实例化一个url对象
            URL url = new URL("http://www.imooc.com/api/teacher?type=3&cid=1");
            // 2.获取HttpURLConnection实例
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 3.设置和请求相关属性
            // 请求方式
            conn.setRequestMethod("GET");
            // 请求超时时长
            conn.setConnectTimeout(6000);
            // 4.获取相应码
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 5.判断相应码并获取相应数据(响应的正文)
                // 获取响应的流
                InputStream inputStream = conn.getInputStream();
                // 在循环中读取输入流
                byte[] b = new byte[1024];// 1G
                int len = 0;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while ((len = inputStream.read(b)) > -1) {
                    // 将字节数组里面的内容存入缓存流
                    // 参数1:待写入数组，参数2:起点，参数3:长度
                    byteArrayOutputStream.write(b, 0, len);
                }
                String msg = new String(byteArrayOutputStream.toString());
                Log.d("----------", msg + "========");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}