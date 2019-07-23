package com.wd.health.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/15 10:39
 * @Description：描述信息
 */
public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "lsjl.db";
    private final static int DB_VERSION = 1;

    public RecordSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStr = "CREATE TABLE IF NOT EXISTS records (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";
        db.execSQL(sqlStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
