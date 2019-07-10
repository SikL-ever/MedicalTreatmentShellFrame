package com.dingtao.common.experiment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.R;
import com.dingtao.common.util.Constant;
//这种格式写activity 给一个艺名，用于其他页面跳转
@Route(path = Constant.ACTIVITY_Ti)
public class TiActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti);
    }
}
