package com.nan.day10_pattern_factory.simple5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.nan.day10_pattern_factory.R;
import com.nan.day10_pattern_factory.simple5.io.IOHandlerFactory;
import com.nan.day10_pattern_factory.simple5.io.handler.IOHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        mTv.setOnClickListener(this);

        IOHandler ioHandler = IOHandlerFactory.getDefaultIOHandler();
        ioHandler.save("name", "huannan5");
        ioHandler.save("age", 28);
    }

    @Override
    public void onClick(View v) {
        IOHandler ioHandler = IOHandlerFactory.getDefaultIOHandler();
        String name = ioHandler.getString("name");
        int age = ioHandler.getInt("age", 0);
        mTv.setText(name + " " + age);
    }
}
