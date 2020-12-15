package com.nan.day32_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testRxJava();
    }

    private void testRxJava() {
        Observable
                .just("1")                                  // ObservableJust 上游       -> 无               下游new ScalarDisposable(MapObserver)
                .map(new Function<String, Integer>() {      // ObservableMap  上游source -> ObservableJust   下游new MapObserver(observer)
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }
                });
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe() {
//                        System.out.println("onSubscribe");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("onNext: " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("onError: " + e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("onComplete");
//                    }
//                });
    }

}
