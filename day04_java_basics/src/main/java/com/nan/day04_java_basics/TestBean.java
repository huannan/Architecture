package com.nan.day04_java_basics;

import android.util.Log;

public class TestBean {

    private String name;

    private TestBean(String name) {
        this.name = name;
    }

    private void test() {
        Log.e("huannan", "test: " + name);
    }

}
