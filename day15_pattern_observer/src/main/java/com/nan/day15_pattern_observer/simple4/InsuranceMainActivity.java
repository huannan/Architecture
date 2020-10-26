package com.nan.day15_pattern_observer.simple4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nan.day15_pattern_observer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式实现保险人员添加功能
 */
public class InsuranceMainActivity extends AppCompatActivity implements Observer<Member> {

    private ListView mMemberLv;
    private List<Member> mMembers;
    private BaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_main);
        mMemberLv = (ListView) findViewById(R.id.member_lv);
        mMembers = new ArrayList<>();
        setAdapter();
        DatabaseManager.getInstance().register(this);
    }

    private void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new BaseAdapter() {
                @Override
                public int getCount() {
                    return mMembers.size();
                }

                @Override
                public Object getItem(int position) {
                    return mMembers.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    // 去掉界面复用优化
                    TextView nameTv = (TextView) LayoutInflater.from(InsuranceMainActivity.this).inflate(R.layout.item_lv, parent, false);
                    nameTv.setText(mMembers.get(position).getName());
                    return nameTv;
                }
            };
            mMemberLv.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void addMember(View view) {
        Intent intent = new Intent(this, InsuranceAddActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // 查询数据库更新列表
    }

    @Override
    public void update(Member member) {
        Log.e("TAG", "name = " + member.getName());
        mMembers.add(member);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseManager.getInstance().unregister(this);
    }
}
