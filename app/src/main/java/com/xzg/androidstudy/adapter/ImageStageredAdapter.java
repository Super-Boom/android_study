package com.xzg.androidstudy.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xzg.androidstudy.R;
import com.xzg.xzglib.apapter.RecyclerArrayAdapter;
import com.xzg.xzglib.data.PictureData;
import com.xzg.xzglib.holder.BaseViewHolder;

/**
 * <pre>
 *     @author xzg
 *     blog  : https://github.com/Super-Boom
 *     time  : 2021/12/02 10:15:34
 *     desc  : 瀑布流图片Adapter
 *     revise:
 * </pre>
 */
public class ImageStageredAdapter extends RecyclerArrayAdapter<PictureData> {
    public ImageStageredAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(parent);
    }


    public class ImageViewHolder extends BaseViewHolder<PictureData> {
        ImageView imgPicture;

        public ImageViewHolder(ViewGroup parent) {
            super(new ImageView(parent.getContext()));
            imgPicture = (ImageView) itemView;
            imgPicture.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        @Override
        public void setData(PictureData data) {
            ViewGroup.LayoutParams params = imgPicture.getLayoutParams();
            // getAdapterPosition 在recyclerView 1.2.0 已经建议使用getBindingAdapterPosition
            int setHeight = getBindingAdapterPosition() % 5;
            // 计算 View 的高度
            int height = 300;
            switch (setHeight) {
                case 0:
                    height = 500;
                    break;
                case 1:
                    height = 750;
                    break;
                case 2:
                    height = 800;
                    break;
                case 3:
                    height = 360;
                    break;
                case 4:
                    height = 660;
                    break;
                default:
                    break;
            }
            params.height = height;
            imgPicture.setLayoutParams(params);
            Glide.with(getContext()).load(data.getImage()).placeholder(R.drawable.avatar1).into(imgPicture);
        }
    }

}
