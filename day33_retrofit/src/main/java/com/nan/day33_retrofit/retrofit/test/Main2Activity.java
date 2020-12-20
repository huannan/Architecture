package com.nan.day33_retrofit.retrofit.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nan.day33_retrofit.R;
import com.nan.day33_retrofit.retrofit.Retrofit;
import com.nan.day33_retrofit.simple1.bean.UserInfo;
import com.nan.day33_retrofit.simple1.net.HttpCallback;
import com.nan.day33_retrofit.simple1.net.ServiceApi;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .build();
        ServiceApi serviceApi = retrofit.create(ServiceApi.class);

        serviceApi.login("huannan", "123456").enqueue(new HttpCallback<UserInfo>() {
            @Override
            public void onSucceed(UserInfo result) {

            }

            @Override
            public void onError(String code, String msg) {

            }
        });
    }
}
