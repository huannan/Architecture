package com.nan.day10_pattern_factory.simple4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.nan.day10_pattern_factory.R;
import com.nan.day10_pattern_factory.simple4.io.IOHandlerFactory;
import com.nan.day10_pattern_factory.simple4.io.PreferencesIOHandlerFactory;
import com.nan.day10_pattern_factory.simple4.io.handler.IOHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        mTv.setOnClickListener(this);

        IOHandlerFactory ioHandlerFactory = new PreferencesIOHandlerFactory();
        IOHandler ioHandler = ioHandlerFactory.createIOHandler();
        ioHandler.save("name", "huannan4");
        ioHandler.save("age", 28);
    }

    @Override
    public void onClick(View v) {
        IOHandlerFactory ioHandlerFactory = new PreferencesIOHandlerFactory();
        IOHandler ioHandler = ioHandlerFactory.createIOHandler();
        String name = ioHandler.getString("name");
        int age = ioHandler.getInt("age", 0);
        mTv.setText(name + " " + age);
    }
}
