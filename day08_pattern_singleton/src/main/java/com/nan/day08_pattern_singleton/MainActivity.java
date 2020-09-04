package com.nan.day08_pattern_singleton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MainActivity");
    }

    public void collect(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}