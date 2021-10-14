package com.xzg.androidstudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xzg.androidstudy.service.TestApi;
import com.xzg.androidstudy.data.TrailProductDetail;
import com.xzg.androidstudy.service.TrailProduct;
import com.xzg.androidstudy.service.impl.TrailProductImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.http.GET;

public class HttpReqActivity extends AppCompatActivity {

    private EditText accEdt, pwdEdt;
    private TextView txt1, txt2;
    private String str;
    // 1.实例化handler
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
//            Toast.makeText(HttpReqActivity.this, "handleMessage", Toast.LENGTH_SHORT).show();
            txt1.setText(str);
            return false;
        }
    });


    // 2.在子线程中发送(空)消息
    // 3.由Handler对象接收消息，并处理
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_req);

        final Button getBtn = findViewById(R.id.get);
        final Button postBtn = findViewById(R.id.post);
        final Button parseJsonBtn = findViewById(R.id.parse);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(v);
            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(v);
            }
        });

        parseJsonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                parseByJSONObject();
                refreshUiByHandler();
            }
        });
        // post
        accEdt = findViewById(R.id.account);
        pwdEdt = findViewById(R.id.pwd);
        txt1 = findViewById(R.id.txt1);
    }


    public void parseByJSONObject() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Call<TrailProductDetail> call = getTrailProductDetail();
                try {
                    TrailProductDetail trailProductDetail = call.execute().body();
                    String trailProductDetailStr = JSON.toJSONString(trailProductDetail);
                    // 解析
                    // 显示到界面上
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txt1.setText(trailProductDetailStr);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    // 使用handler 更细UI状态
    public void refreshUiByHandler() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Call<TrailProductDetail> call = getTrailProductDetail();
                try {
                    TrailProductDetail trailProductDetail = call.execute().body();
                    str = JSON.toJSONString(trailProductDetail);
                    // 解析
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 发送空消息
                handler.sendEmptyMessage(1);
            }
        }.start();
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
            case R.id.post:
                String account = accEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        post(account, pwd);
                    }
                }.start();
                break;
        }
    }

    // get请求
    private String get() {
        try {
            // HttpURLConnection
            // 1.实例化一个url对象
            String path = TestApi.getTrialProductDetail;
            URL url = new URL(path);
            // 2.获取HttpURLConnection实例
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 3.设置和请求相关属性
            // 请求方式
            conn.setRequestMethod("GET");
            // 请求超时时长
            conn.setConnectTimeout(6000);
            // 4.获取相应码
            Log.d("--------res_code", String.valueOf(conn.getResponseCode()));
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
                return msg;
            }
            Log.d("请求异常", "---------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 使用 Retrofit 发送http请求
    private Call<TrailProductDetail> getTrailProductDetail() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://47.96.113.94:12500")
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        GetTrailProductDetail getTrailProductDetail;
        getTrailProductDetail = retrofit.create(GetTrailProductDetail.class);
        Call<TrailProductDetail> call = getTrailProductDetail.getTrailProductDetail();
        return call;
    }

    public interface GetTrailProductDetail {
        @GET("/api/tuitui/getTrialProductDetail?trial_product_code=code1")
        Call<TrailProductDetail> getTrailProductDetail();
    }


    // post请求
    private void post(String account, String pwd) {
        try {
            URL url = new URL("http://www.imooc.com/api/okhttp/postmethod");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(6000);
            // 设置允许输出
            conn.setDoOutput(true);
            // 设置请求数据的类型
            conn.setRequestProperty("Connect-Type", "application/x-www-form-urlencoded");
            // 获取输出流
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(("account=" + account + "&pwd=" + pwd).getBytes());

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