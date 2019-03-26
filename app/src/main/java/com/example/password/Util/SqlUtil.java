package com.example.password.Util;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.password.javaBean.AccountBean;

import java.util.ArrayList;
import java.util.List;

public class SqlUtil {
    private static final String TAG ="SqlUtil" ;
    private final DataHelper helper;
    public SqlUtil(Context context){
        helper=new DataHelper(context);
    }

    public  void insert(AccountBean bean){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql="insert into TB_DATA(title,account,password,date) values('"+bean.getTitle()+"','"+bean.getAccount()+"','"+bean.getPassword()+"','"+bean.getDate()+"')";
        System.out.println(sql);
        db.execSQL(sql);
        db.close();
    }
    public void update(AccountBean bean){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql="update TB_DATA set password='"+bean.getPassword()+"' , date='"+bean.getDate()+"'  where title='"+bean.getTitle()+"' and account='"+bean.getAccount()+"'";
        System.out.println(TAG+sql);
        db.execSQL(sql);
        db.close();
    }
    public void delete(String Title,String account){
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql="delete from   TB_DATA   where title='"+Title+"' and account='"+account+"'";
        System.out.println(TAG+sql);
        db.execSQL(sql);
        db.close();
    }

    public List<AccountBean> select(){
        List<AccountBean> list=new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql="select * from TB_DATA";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            AccountBean bean=new AccountBean();
            int i=cursor.getColumnIndex("title");
            bean.setTitle(cursor.getString(i));
            i=cursor.getColumnIndex("account");
            bean.setAccount(cursor.getString(i));
            i=cursor.getColumnIndex("password");
            Transform transform=new Transform(Constant_pool.encode_str);
            bean.setPassword(transform.decode(cursor.getString(i)));
            i=cursor.getColumnIndex("date");
            bean.setDate(cursor.getString(i));
            list.add(bean);
        }
        return list;
    }

}
