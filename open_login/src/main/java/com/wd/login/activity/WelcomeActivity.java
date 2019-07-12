package com.wd.login.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.util.Constant;
import com.wd.login.R;
import com.wd.login.R2;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends WDActivity {
    @BindView(R2.id.guidancetext)
    TextView guidancetext;
    private SharedPreferences sp;
    private int page=3;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            page--;
            guidancetext.setText(page+"S");
            handler.sendEmptyMessageDelayed(0,1000);
            if (page==0) {
                handler.removeMessages(0);
                //跳转
                intentByRouter(Constant.ACTIVITY_LOGIN_GUIDANCE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("flay",true);
                edit.commit();
                finish();
            }
        }
    };
    //布局
    @Override
    protected int getLayoutId() {
        return R.layout.activity_guidance;
    }
    //初始化
    @Override
    protected void initView() {

        //创建sp存值
        sp = getSharedPreferences("welcomesp", MODE_PRIVATE);
        boolean flay = sp.getBoolean("flay", false);
        if (flay) {
            //跳转登录页面
            //跳转
            intentByRouter(Constant.ACTIVITY_LOGIN_GUIDANCE);
            finish();
        }else{
            //发送一个时间
            handler.sendEmptyMessageDelayed(0,1000);
        }

    }
    //清楚数据
    @Override
    protected void destoryData() {

    }
}
