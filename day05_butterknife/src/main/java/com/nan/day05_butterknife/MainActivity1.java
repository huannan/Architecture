package com.nan.day05_butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nan.day05_butterknife_annotations.BindView;
import com.nan.day05_butterknife_core.ButterKnife;
import com.nan.day05_butterknife_core.Unbinder;

public class MainActivity1 extends AppCompatActivity {

    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.tv2)
    TextView tv2;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        // new MainActivity1_ViewBinding(this);
        mUnbinder = ButterKnife.bind(this);
        tv1.setText("成功");
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}