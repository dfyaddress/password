package com.example.password.Util;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private final static String TAG="DataHepler";

    public DataHelper(@Nullable Context context) {
        super(context, Constant_pool.DB_NAME, null, Constant_pool.version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"创建表");
        String  sql="create table "+Constant_pool.TB_NAME+"(title varchar,account varchar,password varchar,date varchar)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
