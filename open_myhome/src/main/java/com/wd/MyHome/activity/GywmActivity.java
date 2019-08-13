package com.wd.MyHome.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wd.MyHome.R;
import com.wd.MyHome.util.TopView;

public class GywmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gywm);
        TopView topView = findViewById(R.id.gywm_top);
        topView.setTitle("关于我们");
    }
}
