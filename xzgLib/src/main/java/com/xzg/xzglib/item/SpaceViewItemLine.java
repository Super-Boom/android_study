package com.xzg.xzglib.item;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.xzg.xzglib.apapter.RecyclerArrayAdapter;

import static android.widget.LinearLayout.VERTICAL;


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

    private int space;
    private boolean mPaddingEdgeSide = true;
    private boolean mPaddingStart = true;
    private boolean mPaddingHeaderFooter = false;

    public SpaceViewItemLine(int space) {
        this.space = space;
    }

    public void setPaddingEdgeSide(boolean mPaddingEdgeSide) {
        this.mPaddingEdgeSide = mPaddingEdgeSide;
    }

    public void setPaddingStart(boolean mPaddingStart) {
        this.mPaddingStart = mPaddingStart;
    }

    public void setPaddingHeaderFooter(boolean mPaddingHeaderFooter) {
        this.mPaddingHeaderFooter = mPaddingHeaderFooter;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int spanCount = 0;
        int orientation = 0;

        int spanIndex = 0;
        int headerCount = 0, footerCount = 0;
        RecyclerView.Adapter adapter = parent.getAdapter();

        if (adapter == null) {
            return;
        }

        if (adapter instanceof RecyclerArrayAdapter) {
            headerCount = ((RecyclerArrayAdapter) adapter).getHeaderCount();
            footerCount = ((RecyclerArrayAdapter) adapter).getFooterCount();
        }

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        // 瀑布流
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            spanIndex = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        } else if (layoutManager instanceof GridLayoutManager) {
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            spanIndex = ((GridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        } else if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            spanCount = 1;
            spanIndex = 0;
        }

        // 普通Item的尺寸
        if ((position >= headerCount && position < adapter.getItemCount() - footerCount)) {
            if (orientation == VERTICAL) {
                // 元素个数+1 得到间隔数量，间隔数量*间隔宽度=间隔总宽度
                // (父元素宽度-间隔总宽)/元素个数 = 每个元素的宽度
                float expectedWidth = (float) (parent.getWidth() - space * (spanCount + (mPaddingEdgeSide ? 1 : -1))) / spanCount;

                float originWidth = (float) parent.getWidth() / spanCount;
                float expectedX = (mPaddingEdgeSide ? space : 0) + (expectedWidth + space) * spanIndex;
                float originX = originWidth * spanIndex;
                outRect.left = (int) (expectedX - originX);
                outRect.right = (int) (originWidth - outRect.left - expectedWidth);
                if (position - headerCount < spanCount && mPaddingStart) {
                    outRect.top = space;
                }
                outRect.bottom = space;
            }
        }
    }

}
