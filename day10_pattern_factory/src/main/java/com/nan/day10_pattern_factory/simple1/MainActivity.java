package com.nan.day10_pattern_factory.simple1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.nan.day10_pattern_factory.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        mTv.setOnClickListener(this);

        getSharedPreferences("cache", Context.MODE_PRIVATE)
                .edit()
                .putString("name", "huannan1")
                .putInt("age", 28)
                .commit();
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sp = getSharedPreferences("cache", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        int age = sp.getInt("age", 0);
        mTv.setText(name + " " + age);
    }
}
