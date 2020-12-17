package com.nan.day32_rxjava.simple3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.nan.day32_rxjava.R;
import com.nan.day32_rxjava.simple3.rxlogin.RxLogin;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class Main3Activity extends AppCompatActivity {

    public static final String TAG = "Main3Activity";
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }

    public void login(View view) {
        RxLogin rxLogin = new RxLogin(this);
        mDisposable.add(rxLogin
                .login()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer resultCode) throws Exception {
                        Toast.makeText(Main3Activity.this, "resultCode=" + resultCode, Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.clear();
    }
}
