package com.example.password.Util;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.password.Fragment.AllPw;
import com.example.password.R;
import com.example.password.javaBean.AccountBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyAlerrtDialog{
    private Context context;
    private EditText tx_title,tx_num,tv_pw;
    private AlertDialog.Builder builder;
    private View view;
    private boolean flag=false;//标志位，false表示添加，true表示修改
    private  AccountBean bean;
    private AllPw pw;

    public MyAlerrtDialog(Context context, AllPw pw){
        this.pw=pw;
        this.context=context;
    }
    public MyAlerrtDialog(Context context,AccountBean bean){
        this.context=context;
        this.bean=bean;
    }
    public void add_Init(){
        builder=new AlertDialog.Builder(context);
        view=LayoutInflater.from(context).inflate(R.layout.add_item,null);
        tx_title =view.findViewById(R.id.tx_title);
        tx_num=view.findViewById(R.id.tx_num);
        tv_pw=view.findViewById(R.id.tv_pw);
        final AlertDialog dia = builder.setView(view).setTitle("添加新账号").show();
        view.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });
        view.findViewById(R.id.submit_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tx=tx_title.getText().toString()+tx_num.getText().toString()+tv_pw.getText().toString();
                Toast.makeText(context,tx,Toast.LENGTH_SHORT).show();
                bean=new AccountBean();
                bean.setAccount(tx_num.getText().toString());
                bean.setTitle(tx_title.getText().toString());
                Transform transform=new Transform(Constant_pool.encode_str);
                bean.setPassword(transform.encode(tv_pw.getText().toString()));
                SimpleDateFormat df = new SimpleDateFormat("yyyy:MM:dd");//设置日期格式
                bean.setDate(df.format(new Date()));
                SqlUtil sqlUtil=new SqlUtil(context);
                pw.adapter.add(bean);
                sqlUtil.insert(bean);
                Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show();
                dia.dismiss();
            }
        });
    }
    public void edit_Init(){
        builder=new AlertDialog.Builder(context);
        view=LayoutInflater.from(context).inflate(R.layout.add_item,null);
        tx_title =view.findViewById(R.id.tx_title);
        tx_num=view.findViewById(R.id.tx_num);
        tv_pw=view.findViewById(R.id.tv_pw);
        tx_num.setText(bean.getAccount());
        tx_num.setEnabled(false);
        tx_title.setText(bean.getTitle());
        tx_title.setEnabled(false);
        tv_pw.setText(bean.getPassword());
        final AlertDialog dia = builder.setView(view).setTitle("修改密码").show();
        view.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });
        view.findViewById(R.id.submit_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Transform transform=new Transform(Constant_pool.encode_str);
                bean.setPassword(transform.encode(tv_pw.getText().toString()));
                SimpleDateFormat df = new SimpleDateFormat("yyyy:MM:dd");//设置日期格式
                bean.setDate(df.format(new Date()));
                SqlUtil sqlUtil=new SqlUtil(context);
                sqlUtil.update(bean);
                Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
                dia.dismiss();
            }
        });
    }

}
