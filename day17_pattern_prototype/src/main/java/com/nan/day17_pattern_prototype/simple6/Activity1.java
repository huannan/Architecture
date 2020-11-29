package com.nan.day17_pattern_prototype.simple6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nan.day17_pattern_prototype.R;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 三个参数传递到 Activity 2

        Intent intent = new Intent(this, Activity2.class);

        intent.putExtra("Params1", "Params1");
        intent.putExtra("Params2", "Params2");
        intent.putExtra("Params3", "Params3");

        startActivity(intent);
    }
}
