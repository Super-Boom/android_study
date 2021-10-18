package com.xzg.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xzg.androidstudy.data.TrailProductDetail;
import com.xzg.androidstudy.service.impl.TrailProductImpl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.http.HTTP;

public class AsyncTaskActivity extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        txt = findViewById(R.id.get_async_data);

        final Button getTrailProductBtn = findViewById(R.id.async_task_btn);
        getTrailProductBtn.setOnClickListener(new ClickHandler());

    }


    class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.async_task_btn) {
                MyTask myTask = new MyTask();
                myTask.execute();
            }
        }
    }


    class MyTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("TAG", "执行 onPreExecute 方法");
        }

        // 请求服务器
        @Override
        protected String doInBackground(String... strings) {
            TrailProductImpl trailProduct = new TrailProductImpl();
            Call<TrailProductDetail> call = trailProduct.getTrailProductDetail();
            try {
                TrailProductDetail trailProductDetail = call.execute().body();
                Log.d("trailProductDetail---", String.valueOf(trailProductDetail));

                if (trailProductDetail.getCode() == 200) {
                    return JSON.toJSONString(trailProductDetail);
                }
                Log.d("trailProductDetail", String.valueOf(trailProductDetail));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // 显示
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                txt.setText(s);
            }
        }
    }
}