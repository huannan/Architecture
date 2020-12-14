package com.nan.day32_rxjava;

public class Test {

    public static void main(String[] args) {
        Observable.just("abc")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
