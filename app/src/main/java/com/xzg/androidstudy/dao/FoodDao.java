package com.xzg.androidstudy.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

@Entity(indexes = {
        @Index(value = "name,steps,img", unique = true)
})
public class FoodDao {
    private SQLiteDatabase db;

    public FoodDao(Context ctx) {
        String path = ctx.getFilesDir().getAbsolutePath() + "/food.db";
        SQLiteOpenHelper helper = new SQLiteOpenHelper(ctx, path, null, 1) {

            @Override
            public void onCreate(SQLiteDatabase db) {
                String sql = "create table foodTb(" +
                        "_id integer primary key autoincrement," +
                        "name varchar(20)," +
                        "steps varchar(500)," +
                        "img integer)";
                db.execSQL(sql);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };

        // 获取数据库对象
        // 1.如果数据库不存在，则创建，再打开
        // 2.如果数据库存在，但是版本没变化，则直接打开
        // 3.如果数据库存在，但是版本号升高了，则调用升级方法，再打开
        db = helper.getReadableDatabase();
    }
    // 增
    // 删
    // 改
    // 查

}
