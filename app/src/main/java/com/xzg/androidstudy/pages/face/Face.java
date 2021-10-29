package com.xzg.androidstudy.pages.face;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.huawei.hms.mlsdk.MLAnalyzerFactory;
import com.huawei.hms.mlsdk.common.MLFrame;
import com.huawei.hms.mlsdk.face.MLFaceAnalyzer;
import com.xzg.androidstudy.R;

public class Face extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);


        // 创建人脸分析器
        MLFaceAnalyzer mlFaceAnalyzer =  MLAnalyzerFactory.getInstance().getFaceAnalyzer();
        // 通过android.graphics.Bitmap创建MLFrame对象用于分析器检测图片
        MLFrame.fromBitmap();
    }
}