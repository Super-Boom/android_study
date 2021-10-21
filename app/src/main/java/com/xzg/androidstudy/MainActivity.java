package com.xzg.androidstudy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    /**
     * 使用startActivityForResult()方法在activity中传递数据
     * REQUEST_CODE:返回的结果码
     */
    private TextView mTvShow;
    private final static int REQUEST_CODE = 1;

    private static class BtnItem {
        String textViewTitle;
        String btnText;
        int btnId;

        public BtnItem(String textViewTitle, String btnText, int btnId) {
            this.textViewTitle = textViewTitle;
            this.btnText = btnText;
            this.btnId = btnId;

        }
    }

    Map<Integer, Class<?>> map = new HashMap<Integer, Class<?>>() {{
        put(R.id.start_intent, null);
        put(R.id.start_activity, null);
        put(R.id.open_taobao, null);
        put(R.id.open_second_act, BackDataToMain.class);
        put(R.id.open_view_pager, BackDataToMain.class);
        put(R.id.to_tab_page, BackDataToMain.class);
        put(R.id.to_x_tab_page, BackDataToMain.class);
        put(R.id.http_req, BackDataToMain.class);
        put(R.id.timer, BackDataToMain.class);
        put(R.id.to_async_task_page, BackDataToMain.class);
        put(R.id.async_task_progress, BackDataToMain.class);
        put(R.id.card_view_page, BackDataToMain.class);
        put(R.id.share_prefs_demo_btn, BackDataToMain.class);
        put(R.id.test1, null);
        put(R.id.test2, null);
        put(R.id.test3, null);
        put(R.id.test4, null);
    }};

    private BtnItem[] btnList = new BtnItem[]{
            new BtnItem("", "start Intent exp", R.id.start_intent),
            new BtnItem("", "启动activity", R.id.start_activity),
            new BtnItem("", "打开淘宝", R.id.open_taobao),
            new BtnItem("", "打开第二个activity", R.id.open_second_act),
            new BtnItem("", "打开view pager", R.id.open_view_pager),
            new BtnItem("", "跳转到tab页面", R.id.to_tab_page),
            new BtnItem("", "跳转到x_tab页面", R.id.to_x_tab_page),
            new BtnItem("", "网络请求页", R.id.http_req),
            new BtnItem("", "计时器", R.id.timer),
            new BtnItem("", "跳转到异步页面", R.id.to_async_task_page),
            new BtnItem("", "异步任务进度条", R.id.async_task_progress),
            new BtnItem("cardView", "cardview_page", R.id.card_view_page),
            new BtnItem("storage_demo", "shared_prefs_demo_btn", R.id.share_prefs_demo_btn),
            new BtnItem("fragment_demo", "to_fragment_demo_page", R.id.to_fragment_demo_page),
            new BtnItem("", "test1", R.id.test1),
            new BtnItem("", "test2", R.id.test2),
            new BtnItem("", "test3", R.id.test3),
            new BtnItem("", "test4", R.id.test4),
    };


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

        // 动态创建多个按钮
        for (int i = 0; i < btnList.length; i++) {
            Button btn = new Button(MainActivity.this);
            btn.setText(btnList[i].btnText);
            btn.setId(btnList[i].btnId);
            btn.setOnClickListener(new ClickHandler());
            LinearLayout mainAct = (LinearLayout) findViewById(R.id.main_activity);
            mainAct.addView(btn);
        }
    }

    // 点击时间
    class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.start_intent) {
                intentExp();
            } else if (id == R.id.start_activity) {
                startAct();
            } else if (id == R.id.open_taobao) {
                openTbAppOut();
            } else if (id == R.id.open_second_act) {
                Intent intent = new Intent(MainActivity.this, BackDataToMain.class);
                startActivityForResult(intent, REQUEST_CODE);
            } else if (id == R.id.open_view_pager) {
                Intent intent = new Intent(MainActivity.this, ViewPager2Exp.class);
                startActivity(intent);
            } else if (id == R.id.to_tab_page) {
                Intent intent = new Intent(MainActivity.this, SlidingTabActivity.class);
                startActivity(intent);
            } else if (id == R.id.to_x_tab_page) {
                Intent intent = new Intent(MainActivity.this, XTabActivity.class);
                startActivity(intent);
            } else if (id == R.id.http_req) {
                Intent intent = new Intent(MainActivity.this, HttpReqActivity.class);
                startActivity(intent);
            } else if (id == R.id.timer) {
                Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
            } else if (id == R.id.to_async_task_page) {
                Intent intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
                startActivity(intent);
            } else if (id == R.id.async_task_progress) {
                Intent intent = new Intent(MainActivity.this, ProcessBarActivity.class);
                startActivity(intent);
            } else if (id == R.id.card_view_page) {

            } else if (id == R.id.share_prefs_demo_btn) {
                Intent intent = new Intent(MainActivity.this, SharedPrefsActivity.class);
                startActivity(intent);
            } else if (id == R.id.to_fragment_demo_page) {
                Intent intent = new Intent(MainActivity.this, FragmentDemoActivity.class);
                startActivity(intent);
            }



        }
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