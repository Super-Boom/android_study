package com.xzg.androidstudy.pages.face;

import static com.huawei.hms.mlsdk.face.MLFaceShape.TYPE_FACE;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.mlsdk.MLAnalyzerFactory;
import com.huawei.hms.mlsdk.common.MLFrame;
import com.huawei.hms.mlsdk.face.MLFace;
import com.huawei.hms.mlsdk.face.MLFaceAnalyzer;
import com.huawei.hms.mlsdk.face.MLFaceShape;
import com.xzg.androidstudy.R;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Face extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);

        SeekBar seekBar = findViewById(R.id.face_seek_bar);

        // 创建人脸分析器
        MLFaceAnalyzer mlFaceAnalyzer = MLAnalyzerFactory.getInstance().getFaceAnalyzer();
        // 通过android.graphics.Bitmap创建MLFrame对象用于分析器检测图片
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg4);
        MLFrame mlFrame = MLFrame.fromBitmap(bitmap);

        Task<List<MLFace>> task = mlFaceAnalyzer.asyncAnalyseFrame(mlFrame);

        task.addOnSuccessListener(new OnSuccessListener<List<MLFace>>() {
            @Override
            public void onSuccess(List<MLFace> mlFaces) {
                // 检测成功，获取脸部关键点信息
                if (mlFaces.size() <= 0) {
                    return;
                }
                MLFaceShape face = mlFaces.get(0).getFaceShape(TYPE_FACE);
                Log.d("------", String.valueOf(face.getPoints()));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                // 检测失败
            }
        });


        // 检测完成释放分析器
        try {
            if (mlFaceAnalyzer != null) {
                mlFaceAnalyzer.stop();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            int id = seekBar.getId();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


}