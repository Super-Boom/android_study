package com.xzg.androidstudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 计时器例子
 */
public class TimerActivity extends AppCompatActivity {

    private TextView timerTitle, timerNum, timerTxt;
    private ImageView playerBtn, playerBtn2;
    private boolean flag = false;


    // post postDelay postAtTime
    private Handler timerHandler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerTitle = findViewById(R.id.timer_title);
        timerNum = findViewById(R.id.timer_num);
        timerTxt = findViewById(R.id.timer_txt);
        playerBtn = findViewById(R.id.timer_btn);
        playerBtn2 = findViewById(R.id.timer_btn2);

        playerBtn.setOnClickListener(new ClickHandler());
        playerBtn2.setOnClickListener(new ClickHandler());
    }

    private int i;
    private String time;
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int min = i / 60;
            int sec = i % 60;
            time = (min < 10 ? "0" + min : "" + min) + ":" + (sec < 10 ? "0" + sec : "" + sec);
            timerNum.setText(time);
            i++;
            timerHandler2.postDelayed(runnable, 1000);
        }
    };

    // 创建点击事件类
    class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.timer_btn) {
                if (!flag) {
                    flag = true;
                    timerTitle.setText("工作中...");
                    playerBtn.setImageResource(R.mipmap.player_stop);
                    timerTxt.setText("");
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            int i = 1;
                            Log.d("---线程内flag1", flag + "");
                            while (flag) {
                                Log.d("---线程内flag2", flag + "");
                                try {
                                    sleep(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Message msg = new Message();
                                msg.arg1 = i;
                                timerHandler.sendMessage(msg);
                                // 时间 ++
                                i++;
                            }
                        }
                    }.start();
                } else {
                    flag = false;
                }
            } else if (id == R.id.timer_btn2) {
                // 在post方法中，我们可以处理一切和视图相关的操作
                if (!flag) {
                    flag = true;
                    timerTitle.setText("工作中...");
                    playerBtn2.setImageResource(R.mipmap.player_stop);
                    timerTxt.setText("");
                    i = 1;
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            // 延迟执行
                            timerHandler2.postDelayed(runnable, 1000);
                        }
                    }.start();
                } else {
                    flag = false;
                    timerTitle.setText("计时器");
                    playerBtn2.setImageResource(R.mipmap.player_start);
                    timerTxt.setText("用时：" + time);
                    timerHandler2.removeCallbacks(runnable);
                }
            }
        }
    }

    // 用于更新定时器文案
    final Handler timerHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            int min = msg.arg1 / 60;
            int sec = msg.arg1 % 60;
            String time = (min < 10 ? "0" + min : "" + min) + ":" + (sec < 10 ? "0" + sec : "" + sec);
            timerNum.setText(time);
            Log.d("---handler中的flag", flag + "");
            if (!flag) {
                timerTitle.setText("计时器");
                playerBtn.setImageResource(R.mipmap.player_start);
                timerTxt.setText("用时：" + time);
            }
            return false;
        }
    });
}