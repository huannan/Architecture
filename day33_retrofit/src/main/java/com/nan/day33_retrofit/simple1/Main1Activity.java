package com.nan.day33_retrofit.simple1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nan.day33_retrofit.R;
import com.nan.day33_retrofit.simple1.bean.Result;
import com.nan.day33_retrofit.simple1.bean.UserInfo;
import com.nan.day33_retrofit.simple1.net.HttpCallback;
import com.nan.day33_retrofit.simple1.net.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

        Disposable disposable2 = RetrofitClient.getServiceApi()
                .loginRx("huannan", "123456")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<UserInfo>>() {
                    @Override
                    public void accept(Result<UserInfo> userInfoResult) throws Exception {
                        Log.e(TAG, "onSucceed: " + userInfoResult);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "onError: " + throwable.getMessage());
                    }
                });

        // 整合RxJava Retrofit OkHttp示例
        Disposable disposable1 = RetrofitClient.getServiceApi()
                .loginRx("huannan", "123456")
                .compose(RetrofitClient.<UserInfo>getRetryTransformer())
                .subscribe(new Consumer<UserInfo>() {
                    @Override
                    public void accept(UserInfo userInfo) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
