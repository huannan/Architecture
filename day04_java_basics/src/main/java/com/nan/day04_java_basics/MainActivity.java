package com.nan.day04_java_basics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.tv)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // testReflect();

        // ViewUtils.inject(this);
        // tv.setText("success");

        testGenerics();
    }

    private void testGenerics() {
        // 类泛型
        List<String> list = new ArrayList<>();

        // 泛型方法
        tv = viewById(R.id.tv);

        // 上限下限
        startActivity(MainActivity.class);
        startActivity1(Activity.class);
    }

    private <T extends View> T viewById(@IdRes int viewId) {
        return findViewById(viewId);
    }

    /**
     * 上限，包括自己以及子类
     */
    private void startActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * 下限，包括自己以及父类
     */
    private void startActivity1(Class<? super AppCompatActivity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    private void testReflect() {
        try {
            // 拿到构造方法和创建对象
            Constructor<TestBean> testBeanConstructor = TestBean.class.getDeclaredConstructor(String.class);
            testBeanConstructor.setAccessible(true);
            Object testBean = testBeanConstructor.newInstance("哈哈");

            // 访问属性
            Field declaredField = TestBean.class.getDeclaredField("name");
            declaredField.setAccessible(true);
            String name = (String) declaredField.get(testBean);
            Log.e("huanann", "onCreate: " + name);

            // 访问方法
            Method declaredMethod = Class.forName("com.nan.day04_java_basics.TestBean").getDeclaredMethod("test");
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(testBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}