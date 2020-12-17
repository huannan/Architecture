package com.nan.day32_rxjava.simple3.loginsdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nan.day32_rxjava.R;

public class LoginActivity extends AppCompatActivity {

    public static final int CODE_LOGIN_SUCCESS = 1;
    public static final int CODE_LOGIN_FAILED = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginSuccess(View view) {
        setResult(CODE_LOGIN_SUCCESS);
        finish();
    }

    public void loginFailed(View view) {
        setResult(CODE_LOGIN_FAILED);
        finish();
    }
}
