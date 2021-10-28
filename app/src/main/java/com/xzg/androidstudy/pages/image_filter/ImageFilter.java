package com.xzg.androidstudy.pages.image_filter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.xzg.androidstudy.R;
import com.xzg.androidstudy.utils.GlideEngine;

import java.io.File;
import java.util.List;

import jp.co.cyberagent.android.gpuimage.GPUImageView;

public class ImageFilter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_filter);

        Button imgSelectBtn = findViewById(R.id.add_log);
        imgSelectBtn.setOnClickListener(new ClickHandler());

    }

    // 添加点击事件
    public class ClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.add_log) {
                pictureSelect();
            }
        }
    }

    // 打开相册
    private void pictureSelect() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine())
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureConfig.CHOOSE_REQUEST) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            Log.d("-------------selectList", String.valueOf(selectList));
            showSelectPic(selectList);
        }
    }


    // 展示图片
    private void showSelectPic(List<LocalMedia> result) {
        GPUImageView view = findViewById(R.id.img_container);
        for (LocalMedia l:result){
            String path = l.getRealPath();
            view.setImage(new File(path));
        }
    }
}