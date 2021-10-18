package com.xzg.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProcessBarActivity extends AppCompatActivity {

    private TextView text;
    private ImageView start, cancel;
    private ProgressBar progressBar;
    private ProgressTask pTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_bar);

        progressBar = findViewById(R.id.progress_bar);
        start = findViewById(R.id.start);
        cancel = findViewById(R.id.cancel);
        text = findViewById(R.id.text);
        pTask = new ProgressTask();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pTask.isCancelled()){
                    pTask = new ProgressTask();
                }
                pTask.execute();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pTask.cancel(true);
            }
        });
    }

    // 定义一个AsyncTask类
    // 实例化子类
    // 启动（——>取消）
    // onPreExecute(text:加载中)——>doInBackGround——>onProgressUpdate——>onPostExecute
    // 取消   ——>onCancelled

    /**
     * Params: execute 方法的参数类型，doInBackground 方法的参数类型
     * Progress: 进度
     * Result:
     */
    class ProgressTask extends AsyncTask<Void, Integer, String> {
        // 执行线程任务之前的操作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            text.setText("加载中");
        }

        // 作用：接受输入参数，执行任务中的耗时操作，返回线程任务的执行结果
        @Override
        protected String doInBackground(Void... voids) {
            try {
                for (int i = 1; i <= 100; i++) {
                    publishProgress(i);
                    Thread.sleep(50);
                }
                return "加载完毕";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        // 在主县城中显示线程任务的执行速度，在doInBackground 方法中调用publishProgress方法则触发该方法
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            text.setText("加载..." + values[0] + "%");
        }

        // 接受线程任务的执行结果，将执行结果显示在界面上
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                text.setText(s);
            }
            pTask = new ProgressTask();
        }

        // 取消异步任务时触发该方法
        @Override
        protected void onCancelled() {
            super.onCancelled();
            text.setText("已取消");
            progressBar.setProgress(0);
        }
    }
}