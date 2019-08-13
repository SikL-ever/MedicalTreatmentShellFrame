package com.bw.message.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bw.message.childactivity.HActivity;
import com.bw.message.childactivity.InquiryActivity;
import com.bw.message.childactivity.SystemActivity;
import com.bw.message.presenter.MyUserLookPresenter;
import com.bw.message.presenter.MyUserNoLookPresenter;
import com.dingtao.common.bean.MyUserNoBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

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
    @BindView(R2.id.systext)
    TextView systext;
    @BindView(R2.id.intext)
    TextView intext;
    @BindView(R2.id.htext)
    TextView htext;
    @BindView(R2.id.topviewtextall)
    TextView topviewtextall;
    @BindView(R2.id.messagelinera)
    LinearLayout messagelinera;
    private MyUserNoLookPresenter myUserNoLookPresenter;
    private MyUserLookPresenter myUserLookPresenter;
    private List<String> intt;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_message;
    }

    @Override
    protected void initView() {
        //设置全部已读
        myUserLookPresenter = new MyUserLookPresenter(new getdatalook());
        topviewtextall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myUserLookPresenter.reqeust(intt.get(0), intt.get(1));
            }
        });
        //未读数
        myUserNoLookPresenter = new MyUserNoLookPresenter(new getdata());
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
                Intent intent = new Intent(MyUserMessageActivity.this, HActivity.class);
                startActivity(intent);
            }
        });
        //系统消息
        mysystemMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyUserMessageActivity.this, SystemActivity.class);
                startActivity(intent);
            }
        });
        myinquiryMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyUserMessageActivity.this, InquiryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void destoryData() {

    }

    //*********************************************
    class getdata implements DataCall<List<MyUserNoBean>> {
        @Override
        public void success(List<MyUserNoBean> data, Object... args) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).noticeType == 1) {
                    if (data.get(i).notReadNum == 0) {
                        systext.setVisibility(View.GONE);
                    } else {
                        systext.setVisibility(View.VISIBLE);
                        systext.setText(data.get(i).notReadNum + "");
                    }
                } else if (data.get(i).noticeType == 2) {
                    if (data.get(i).notReadNum == 0) {
                        intext.setVisibility(View.GONE);
                    } else {
                        intext.setVisibility(View.VISIBLE);
                        intext.setText(data.get(i).notReadNum + "");
                    }
                } else if (data.get(i).noticeType == 3) {
                    if (data.get(i).notReadNum == 0) {
                        htext.setVisibility(View.GONE);
                    } else {
                        htext.setVisibility(View.VISIBLE);
                        htext.setText(data.get(i).notReadNum + "");
                    }
                }
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class getdatalook implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            Snackbar.make(messagelinera, "已读成功", Snackbar.LENGTH_SHORT).show();
            myUserNoLookPresenter.reqeust(intt.get(0), intt.get(1));
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        intt = new LoginDaoUtil().intt(this);
        myUserNoLookPresenter.reqeust(intt.get(0), intt.get(1));
    }
}
