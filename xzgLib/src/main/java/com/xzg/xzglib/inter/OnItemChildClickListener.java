package com.xzg.xzglib.inter;

import android.view.View;

/**
 * <pre>
 *     @author xzg
 *     blog  : https://github.com/Super-Boom
 *     time  : 2021/11/29 18:16:17
 *     desc  : item元素点击监听接口
 *     revise: 
 * </pre>
 */
public interface OnItemChildClickListener {

    /**
     * item中元素点击监听接口
     * @param view     view
     * @param position position 索引
     */
    void onItemChildClick(View view, int position);
}
