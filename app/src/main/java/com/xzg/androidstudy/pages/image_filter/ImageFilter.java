package com.xzg.androidstudy.pages.image_filter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.xzg.androidstudy.R;
import com.xzg.androidstudy.utils.GlideEngine;

import java.io.File;
import java.util.List;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageView;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSharpenFilter;

public class ImageFilter extends AppCompatActivity {


    private GPUImage gpuImage;
    private SeekBar seekBar;
    private GPUImageView gpuImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_filter);
        gpuImageView = findViewById(R.id.img_container);
        // 获取进度条
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        Button imgSelectBtn = findViewById(R.id.add_log);
        // 设置灰色滤镜按钮
        Button grayBtn = findViewById(R.id.set_gray);
        imgSelectBtn.setOnClickListener(new ClickHandler());
        grayBtn.setOnClickListener(new ClickHandler());

        // 通过进度条设置滤镜
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setGrayFilter(gpuImageView,progress);
                gpuImageView.requestRender();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    // 添加点击事件
    public class ClickHandler implements View.OnClickListener {
        GPUImageView view = findViewById(R.id.img_container);

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.add_log) {
                pictureSelect();
            } else if (id == R.id.set_gray) {
                setGrayFilter(view,0);
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

            // 清理缓存
            PictureFileUtils.deleteCacheDirFile(ImageFilter.this, PictureMimeType.ofImage());
        }
    }


    // 展示图片
    private void showSelectPic(List<LocalMedia> result) {
        GPUImageView view = findViewById(R.id.img_container);
        for (LocalMedia l : result) {
            String path = l.getRealPath();
            view.setImage(new File(path));
        }
    }

    // 设置灰色滤镜
    private void setGrayFilter(GPUImageView view,int percent) {
        GPUImageSharpenFilter gpuImageSharpenFilter = new GPUImageSharpenFilter();
        gpuImageSharpenFilter.setSharpness(percent);
        view.setFilter(gpuImageSharpenFilter);
    }
}