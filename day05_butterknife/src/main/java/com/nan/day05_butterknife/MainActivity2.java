package com.nan.day05_butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nan.day05_butterknife_annotations.BindView;
import com.nan.day05_butterknife_core.ButterKnife;
import com.nan.day05_butterknife_core.Unbinder;

public class MainActivity2 extends AppCompatActivity {

    @BindView(R.id.tv3)
    TextView tv3;

    @BindView(R.id.tv4)
    TextView tv4;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mUnbinder = ButterKnife.bind(this);
        tv3.setText("成功");
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}