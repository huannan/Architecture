package com.nan.day32_rxjava.simple3.rxlogin;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxLogin {

    public static final String INTENT_ARG_SUBJECT_ID = "intent_arg_subject_id";
    private static Map<Integer, PublishSubject<Integer>> SUBJECTS = new HashMap<>();
    private Activity mActivity;

    public RxLogin(Activity activity) {
        mActivity = activity;
    }

    public Observable<Integer> login() {
        int subjectId = mActivity.hashCode();

        Intent intent = new Intent(mActivity, RxLoginActivity.class);
        intent.putExtra(INTENT_ARG_SUBJECT_ID, subjectId);
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(0, 0);

        List<Observable<Integer>> list = new ArrayList<>(1);
        list.add(findSubject(subjectId));
        return Observable.concat(list);
    }

    public static PublishSubject<Integer> findSubject(int subjectId) {
        PublishSubject<Integer> subject;
        if (SUBJECTS.containsKey(subjectId)) {
            subject = SUBJECTS.get(subjectId);
        } else {
            subject = PublishSubject.create();
            SUBJECTS.put(subjectId, subject);
        }
        return subject;
    }
}
