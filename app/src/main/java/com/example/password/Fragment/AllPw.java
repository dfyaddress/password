package com.example.password.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.password.Adapter.ListAdapter;
import com.example.password.R;
import com.example.password.Util.Constant_pool;
import com.example.password.Util.SqlUtil;
import com.example.password.Util.Transform;

public class AllPw extends Fragment {
    public ListAdapter adapter;
    private RecyclerView recyclerView;
    private SqlUtil sqlUtil;
    private  View view;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.show,container,false);
        sqlUtil=new SqlUtil(view.getContext());
        adapter=new ListAdapter(view.getContext(),sqlUtil.select());
        recyclerView=view.findViewById(R.id.reyclerview);
        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        return view;
    }
}
