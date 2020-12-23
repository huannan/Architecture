package com.nan.day33_retrofit.simple2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nan.day33_retrofit.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                TextView textView = findViewById(R.id.tv);
                textView.setText("哈哈");
            }
        }).start();
        */


    }
}