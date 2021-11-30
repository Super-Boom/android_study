package com.xzg.xzglib.inter;

import android.view.View;
import android.view.ViewGroup;

/**
 * <pre>
 *     @author xzg
 *     blog  : https://github.com/Super-Boom
 *     time  : 2021/11/30 14:13:03
 *     desc  :
 *     revise:
 * </pre>
 */
public interface InterItemView {
    /**
     * 创建view
     *
     * @param parent parent
     * @return view
     */
    View onCreateView(ViewGroup parent);


    /**
     * 绑定view
     *
     * @param headerView headerView
     */
    void onBindView(View headerView);
}
