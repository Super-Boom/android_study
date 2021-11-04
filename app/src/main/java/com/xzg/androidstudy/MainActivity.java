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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xzg.androidstudy.entity.Student;
import com.xzg.androidstudy.pages.broadcast.BroadcastActivity;
import com.xzg.androidstudy.pages.face.Face;
import com.xzg.androidstudy.pages.food.FoodActivity;
import com.xzg.androidstudy.pages.image_filter.ImageFilter;
import com.xzg.androidstudy.pages.student_room_example.StudentActivity;

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
        Class<?> actClass;

        public BtnItem(String textViewTitle, String btnText, int btnId, Class<?> actClass) {
            this.textViewTitle = textViewTitle;
            this.btnText = btnText;
            this.btnId = btnId;
            this.actClass = actClass;
        }
    }

    private final BtnItem[] btnList = new BtnItem[]{
            new BtnItem("", "start Intent exp", R.id.start_intent, null),
            new BtnItem("", "启动activity", R.id.start_activity, null),
            new BtnItem("", "打开淘宝", R.id.open_taobao, null),
            new BtnItem("", "打开第二个activity", R.id.open_second_act, BackDataToMain.class),
            new BtnItem("", "打开view pager", R.id.open_view_pager, ViewPager2Exp.class),
            new BtnItem("", "跳转到tab页面", R.id.to_tab_page, SlidingTabActivity.class),
            new BtnItem("", "跳转到x_tab页面", R.id.to_x_tab_page, XTabActivity.class),
            new BtnItem("", "网络请求页", R.id.http_req, HttpReqActivity.class),
            new BtnItem("", "计时器", R.id.timer, TimerActivity.class),
            new BtnItem("", "跳转到异步页面", R.id.to_async_task_page, AsyncTaskActivity.class),
            new BtnItem("", "异步任务进度条", R.id.async_task_progress, ProcessBarActivity.class),
            new BtnItem("cardView", "cardview_page", R.id.card_view_page, null),
            new BtnItem("storage_demo", "试用sharePreferences本地储存", R.id.share_prefs_demo_btn, SharedPrefsActivity.class),
            new BtnItem("fragment_demo", "to_fragment_demo_page", R.id.to_fragment_demo_page, FragmentDemoActivity.class),
            new BtnItem("", "跳转到SQLite页面", R.id.to_sqlite_page, FoodActivity.class),
            new BtnItem("", "跳转发布笔记页面", R.id.to_add_log_page, ImageFilter.class),
            new BtnItem("", "跳转到美颜页面", R.id.to_face_page, Face.class),
            new BtnItem("", "跳转到room例子页面", R.id.to_room_example_page, StudentActivity.class),
            new BtnItem("", "跳转到广播页", R.id.to_broadcast_page, BroadcastActivity.class),
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
            btn.setOnClickListener(new ClickHandler(btnList[i].actClass));
            LinearLayout mainAct = (LinearLayout) findViewById(R.id.main_activity);
            mainAct.addView(btn);
        }
    }

    // 点击时间
    class ClickHandler implements View.OnClickListener {
        private final Class<?> actClass;

        public ClickHandler(Class<?> actClass) {
            this.actClass = actClass;
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.start_intent) {
                intentExp();
            } else if (id == R.id.start_activity) {
                startAct();
            } else if (id == R.id.open_taobao) {
                openTbAppOut();
            } else if (this.actClass != null) {
                Intent intent = new Intent(MainActivity.this, this.actClass);
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