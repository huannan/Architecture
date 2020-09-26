package com.nan.day11_pattern_decorator.simple3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nan.day11_pattern_decorator.R;
import com.nan.day11_pattern_decorator.simple3.recyclerview.WrapRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WrapRecyclerView mRecyclerView;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("position: " + i);
        }

        mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 用最少知识的原则去改造代码,使得使用者不需要知道WrapRecyclerAdapter的存在
        // 笔记:不要把代码过度封装,业务逻辑能分开就分开,不含业务逻辑的底层和中间层能封装就封装不用纠结过度封装
        // 使用装饰设计模式,使其支持添加头部和尾部
        /*
        WrapRecyclerAdapter adapter = new WrapRecyclerAdapter(new MyAdapter());
        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_header, mRecyclerView, false);
        adapter.addHeaderView(headerView);
        View footerView = LayoutInflater.from(this).inflate(R.layout.layout_footer, mRecyclerView, false);
        adapter.addFooterView(footerView);
        mRecyclerView.setAdapter(adapter);
        */

        mRecyclerView.setAdapter(new MyAdapter());
        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_header, mRecyclerView, false);
        View footerView = LayoutInflater.from(this).inflate(R.layout.layout_footer, mRecyclerView, false);
        mRecyclerView.addHeaderView(headerView);
        mRecyclerView.addFooterView(footerView);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
            return new MyViewHolder(itemVIew);
        }

        @SuppressLint("RecyclerView")
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int position) {
            viewHolder.mTv.setText(mData.get(position));
            viewHolder.mTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mData.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView mTv;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                mTv = itemView.findViewById(R.id.tv);
            }
        }
    }
}
