package com.bw.message.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bw.message.childactivity.HActivity;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.util.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import health.wd.com.open_mymessage.R;
import health.wd.com.open_mymessage.R2;

@Route(path = Constant.ACTIVITY_MYUSERMESSAGE)
public class MyUserMessageActivity extends WDActivity {


    @BindView(R2.id.mymessagefinish)
    ImageView mymessagefinish;
    @BindView(R2.id.mysystemMessage)
    LinearLayout mysystemMessage;
    @BindView(R2.id.myinquiryMessage)
    LinearLayout myinquiryMessage;
    @BindView(R2.id.myHMessage)
    LinearLayout myHMessage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_message;
    }

    @Override
    protected void initView() {
        //退出
        mymessagefinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //Hbi详细信息
        myHMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyUserMessageActivity.this,HActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void destoryData() {

    }

}
