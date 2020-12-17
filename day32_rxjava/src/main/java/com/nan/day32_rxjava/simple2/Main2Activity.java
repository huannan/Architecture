package com.nan.day32_rxjava.simple2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.nan.day32_rxjava.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * RxJava实际使用场景
 */
public class Main2Activity extends AppCompatActivity {

    private EditText mUserNameEt, mUserPwdEt;
    private View mClearContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // ShareApplication.attach(this);

        mUserNameEt = (EditText) findViewById(R.id.user_name_et);
        mUserPwdEt = (EditText) findViewById(R.id.user_password_et);
        mClearContent = findViewById(R.id.clear_content);
        log("main");

        // addTextChangedListener
        RxTextView.textChanges(mUserNameEt).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence text) throws Exception {
                log("textChanges");
                mClearContent.setVisibility(TextUtils.isEmpty(text) ? View.INVISIBLE : View.VISIBLE);
            }
        });
        Observable<CharSequence> userNameObservable = RxTextView.textChanges(mUserNameEt);
        Observable<CharSequence> userPwdObservable = RxTextView.textChanges(mUserPwdEt);

        // 相当于合并
        Observable.combineLatest(userNameObservable, userPwdObservable,
                new BiFunction<CharSequence, CharSequence, Object>() {
                    @Override
                    public Object apply(CharSequence userName, CharSequence userPwd) throws Exception {
                        // 设置按钮是否可用(或者改变背景颜色)
                        log("combineLatest");
                        mClearContent.setEnabled(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userPwd));
                        return null;
                    }
                });

        // 防止重复点击 debounce（时间，时间的单位）
        RxView.clicks(mClearContent).debounce(300, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        });

        // 控件操作时间间隔
        RxTextView.textChanges(mUserNameEt).debounce(1200, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        log("debounce");
                    }
                });

        // 接口轮询，轮询操作
        Observable.interval(2, 2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        log("interval");
                    }
                });

        // 延时操作
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        log("timer");
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void log(String text) {
        Log.e("TAG", text + " : " + Thread.currentThread().getName());
    }

}
