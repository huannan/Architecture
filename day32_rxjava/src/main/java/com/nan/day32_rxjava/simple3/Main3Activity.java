package com.nan.day32_rxjava.simple3;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nan.day32_rxjava.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * RxJava实际使用场景-RxPermissions
 */
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isSuccess) throws Exception {

                    }
                });
    }
}
