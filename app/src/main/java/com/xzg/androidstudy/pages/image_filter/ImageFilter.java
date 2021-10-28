package com.xzg.androidstudy.pages.image_filter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.xzg.androidstudy.R;
import com.xzg.androidstudy.utils.GlideEngine;

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

}