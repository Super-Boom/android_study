package com.xzg.xzglib.utils;

import android.app.Application;
import android.content.Context;

/**
 * <pre>
 *     @author xzg
 *     blog  : https://github.com/Super-Boom
 *     time  : 2021/11/30 14:58:26
 *     desc  : 工具类
 *     revise:
 * </pre>
 */
public class RecyclerUtils {
    public static void checkContent(Context context) {
        if (context == null) {
            throw new NullPointerException("context is not null");
        }
        if(context instanceof Application){
            throw new UnsupportedOperationException("context is not application");
        }
    }
}
