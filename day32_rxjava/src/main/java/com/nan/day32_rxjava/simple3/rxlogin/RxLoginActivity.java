package com.nan.day32_rxjava.simple3.rxlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nan.day32_rxjava.simple3.loginsdk.LoginSDK;

import io.reactivex.subjects.PublishSubject;

public class RxLoginActivity extends Activity {

    private int mSubjectId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubjectId = getIntent().getIntExtra(RxLogin.INTENT_ARG_SUBJECT_ID, 0);

        LoginSDK.login(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PublishSubject<Integer> subject = RxLogin.findSubject(mSubjectId);
        subject.onNext(resultCode);
        finish();
        overridePendingTransition(0, 0);
    }
}
