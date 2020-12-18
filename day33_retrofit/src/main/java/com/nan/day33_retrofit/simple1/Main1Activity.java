package com.nan.day33_retrofit.simple1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nan.day33_retrofit.R;
import com.nan.day33_retrofit.simple1.bean.UserInfo;
import com.nan.day33_retrofit.simple1.net.HttpCallback;
import com.nan.day33_retrofit.simple1.net.RetrofitClient;

public class Main1Activity extends AppCompatActivity {

    private static final String TAG = "Main1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitClient.getServiceApi()
                .login("huannan", "123456")
                .enqueue(new HttpCallback<UserInfo>() {
                    @Override
                    public void onSucceed(UserInfo result) {
                        Log.e(TAG, "onSucceed: " + result);
                    }

                    @Override
                    public void onError(String code, String msg) {
                        Log.e(TAG, "onError: " + msg);
                    }
                });
    }
}
