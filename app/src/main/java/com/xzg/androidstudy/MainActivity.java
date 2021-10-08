package com.xzg.androidstudy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /**
     * 使用startActivityForResult()方法在activity中传递数据
     * REQUEST_CODE:返回的结果码
     */
    private Button mBtnStart;
    private TextView mTvShow;
    private final static int REQUEST_CODE = 1;

    /**
     * @param requestCode 请求码，即调用startActivityForResult()传递过去的值
     * @param resultCode  返回码，结果码用于标识返回数据来自哪个新的Activity
     * @param data        更新厚的数据
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                mTvShow.setText(data.getExtras().getString("second") + "requestCode:" + requestCode + "resultCode:" + resultCode);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 添加点击事件
        final Button startIntentExp = findViewById(R.id.start_intent_exp);
        startIntentExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentExp();
            }
        });

        // 启动另外一个activity
        final Button startActivity = findViewById(R.id.start_activity);
        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAct();
            }
        });

        // 打开淘宝
        final Button openTb = findViewById(R.id.open_tb);
        openTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTbAppOut();
            }
        });


        mBtnStart = findViewById(R.id.btn_start);
        mTvShow = findViewById(R.id.tv_shows);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获得新打开的Activity关闭后返回的数据
                // 第二个参数为请求码，可以根据业务需求自己编号
                Intent intent = new Intent(MainActivity.this, BackDataToMain.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    // 点击事件
    protected void intentExp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        String textMessage = "这是一条来自外太空的消息";
        // 发送message信息
        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
        startActivity(sendIntent);
    }

    // 启动另外一个activity
    protected void startAct() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "794489107@qq.com");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "邮件测试");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "测试");
        startActivity(emailIntent);
    }

    // 打开淘宝app
    protected void openTbAppByInner() {
        String url2 = "https://detail.tmall.com/item.htm?id=649597458337&spm=a21bo.21814703.201876.5.5af911d9e7TU5M&scm=1007.34127.227518.0&pvid=1594139a-1e09-448f-8936-c44fa809681b";
        if (checkPackage(this, "com.taobao.taobao")) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            Uri uri = Uri.parse(url2);
            intent2.setData(uri);
            intent2.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
            startActivity(intent2);
        }
    }

    // 外部打开淘宝app
    protected void openTbAppOut() {
        String url2 = "https://detail.tmall.com/item.htm?id=649597458337&spm=a21bo.21814703.201876.5.5af911d9e7TU5M&scm=1007.34127.227518.0&pvid=1594139a-1e09-448f-8936-c44fa809681b";
        if (checkPackage(this, "com.taobao.taobao")) {
            Intent intent2 = getPackageManager().getLaunchIntentForPackage("com.taobao.taobao"); //这行代码比较重要
            intent2.setAction("android.intent.action.VIEW");
            intent2.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
            Uri uri = Uri.parse(url2);
            intent2.setData(uri);
            startActivity(intent2);
        }
    }

    // 校验是否安装淘宝
    public static boolean checkPackage(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    // 保存状态方法
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}