package com.nan.day08_pattern_singleton;

import android.os.Bundle;
import android.view.View;

import com.nan.day08_pattern_singleton.manager.ActivityManager;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("RegisterActivity");
    }

    public void complete(View view) {
        // 注册完成
        ActivityManager.getInstance().finish(this);
        ActivityManager.getInstance().finish(LoginActivity.class);
    }
}
