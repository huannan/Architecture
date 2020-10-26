package com.nan.day15_pattern_observer.simple4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.nan.day15_pattern_observer.R;

/**
 * 保险添加人员界面
 */
public class InsuranceAddActivity extends AppCompatActivity {
    private EditText mNameEt, mAgeEt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_add);

        mNameEt = (EditText) findViewById(R.id.name_et);
        mAgeEt = (EditText) findViewById(R.id.age_et);
    }

    public void add(View view) {
        // 添加购买保险的人员

        // 插入数据库 (这步肯定是不能少的)
        DatabaseManager.getInstance()
                .insert(new Member(mNameEt.getText().toString(), mAgeEt.getText().toString()));

        // 怎么去通知我们的主Activity去更新？
        // 1. 可以当关闭的时候 setResult 会调用 MainActivity 的 onActivityResult

        // 2. 可以利用 EventBus 去发送更新

        // 3. 还有就是当页面关闭的时候我可以去读取数据库

        // 4. 利用观察者模式去更新我们的列表
    }

    public void finish(View view) {
        finish();
    }
}
