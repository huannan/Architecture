package com.nan.day09_pattern_builder;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.nan.day09_pattern_builder.navigation.AbsNavigationBar;
import com.nan.day09_pattern_builder.navigation.DefaultNavigationBar;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private ViewGroup mViewGroupRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewGroupRoot = findViewById(R.id.view_root);
        // test1();
        test2();
    }

    private void test2() {
        /*
        new NavigationBar.Builder(this, R.layout.view_navigation_bar, mViewGroupRoot)
                .build();
        */

        DefaultNavigationBar navigationBar = new DefaultNavigationBar.Builder(this, mViewGroupRoot)
                .setLeftText("返回", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .setLeftTextVisible(View.VISIBLE)
                .build();

        // 在写代码的时候一个是 高扩展 ，并不是要把所有的内容和出现的问题都想到，而是在新增加功能时候可以保证原来的代码不变
        // 对于开发者来说需要用好 最少知识原则，使用者是并不想关注太多

        // 如果你想设置其他属性呢？比如文字大小，比如文字颜色，设置图片？等等
        /*
        View view = navigationBar.findViewById(R.id.tv_left);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        */
    }

    private void test1() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        // 对比
        /*
        OkHttpClient client = new OkHttpClient();
        client.connectTimeout(10, TimeUnit.SECONDS);
        ....
         */

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("标题")
                .setIcon(R.mipmap.ic_launcher)
                .create();
        dialog.show();
    }
}
