package com.xzg.xzglib.item;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <pre>
 *     @author xzg
 *     blog  : https://github.com/Super-Boom
 *     time  : 2021/12/03 18:15:48
 *     desc  : list条目的分割线
 *     revise:
 * </pre>
 */
public class SpaceViewItemLine extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int spanCount = 0;
        int orientation = 0;

        int spanIndex = 0;
        int headerCount = 0, footerCount = 0;
        RecyclerView.Adapter adapter = parent.getAdapter();

    }

}
