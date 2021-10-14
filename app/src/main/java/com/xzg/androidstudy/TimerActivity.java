package com.xzg.androidstudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 计时器例子
 */
public class TimerActivity extends AppCompatActivity {

    private TextView timerTitle, timerNum, timerTxt;
    private ImageView playerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerTitle = findViewById(R.id.timer_title);
        timerNum = findViewById(R.id.timer_num);
        timerTxt = findViewById(R.id.timer_txt);
        playerBtn = findViewById(R.id.timer_btn);

        playerBtn.setOnClickListener(new ClickHandler());
    }

    // 创建点击事件类
    class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.timer_btn) {
                timerTitle.setText("工作中...");
                playerBtn.setImageResource(R.mipmap.player_stop);

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        int i = 1;
                        while (true) {
                            try {
                                sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            i++;
                        }
                    }
                }.start();
            }
        }
    }

    // 用于更新定时器文案
    final Handler timerHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            return false;
        }
    });
}