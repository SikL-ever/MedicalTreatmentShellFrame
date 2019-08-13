package com.wd.MyHome.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wd.MyHome.R;
import com.wd.MyHome.util.TopView;

public class BzzxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bzzx);
        TopView topView = findViewById(R.id.bbgx_top);
        topView.setTitle("帮助中心");
    }
}
