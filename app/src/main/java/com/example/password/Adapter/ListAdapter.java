package com.example.password.Adapter;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.password.R;
import com.example.password.Util.MyAlerrtDialog;
import com.example.password.Util.SqlUtil;
import com.example.password.javaBean.AccountBean;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private Context context;
    private List<AccountBean> data;

    public ListAdapter(Context context,List<AccountBean> data) {
        this.context=context;
        this.data=data;

    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private ClipboardManager cm;
        private ClipData mClipData;
        private TextView tv_list_title,tv_list_num,tv_list_date;
        private String  password;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

            tv_list_title=itemView.findViewById(R.id.tv_list_title);
            tv_list_num=itemView.findViewById(R.id.tv_list_num);
            tv_list_date=itemView.findViewById(R.id.tv_list_date);
            password="";
            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View view = LayoutInflater.from(context).inflate(R.layout.edit_item, null);
                    final AlertDialog dia=builder.setView(view).show();
                    view.findViewById(R.id.bt_copy).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mClipData=ClipData.newPlainText("密码",password);
                            cm.setPrimaryClip(mClipData);
                            Toast.makeText(context,"复制成功",Toast.LENGTH_SHORT).show();
                            dia.dismiss();
                        }
                    });
                    view.findViewById(R.id.bt_edit).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AccountBean bean=new AccountBean();
                            bean.setDate(tv_list_date.getText().toString());
                            bean.setPassword(password);
                            bean.setAccount(tv_list_num.getText().toString());
                            bean.setTitle(tv_list_title.getText().toString());
                            MyAlerrtDialog myAlerrtDialog=new MyAlerrtDialog(context,bean);
                            myAlerrtDialog.edit_Init();
                            dia.dismiss();

                        }
                    });
                    view.findViewById(R.id.bt_del).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SqlUtil sqlUtil=new SqlUtil(context);
                            sqlUtil.delete(tv_list_title.getText().toString(),tv_list_num.getText().toString());
                            delete(tv_list_title.getText().toString(),tv_list_num.getText().toString());
                            Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                            ListAdapter.this.notifyDataSetChanged();
                            dia.dismiss();

                        }
                    });



                    return false;
                }
            });

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=View.inflate(context, R.layout.show_item,null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.tv_list_title.setText(data.get(i).getTitle());
        viewHolder.tv_list_num.setText(data.get(i).getAccount());
        viewHolder.tv_list_date.setText(data.get(i).getDate());
        viewHolder.password=data.get(i).getPassword();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void delete(AccountBean bean){
        for(int i=0;i<data.size();i++){
            if(data.get(i).getTitle().equals(bean.getTitle())&&data.get(i).getAccount().equals(bean.getAccount())){
                data.remove(i);
            }
        }
    }
    public void delete(String Title,String account){
        for(int i=0;i<data.size();i++){
            if(data.get(i).getTitle().equals(Title)&&data.get(i).getAccount().equals(account)){
                data.remove(i);
            }
        }
    }

    public void add(AccountBean bean){
        data.add(bean);
    }

}
