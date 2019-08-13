package com.bw.message.childactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bw.message.adapter.MyUserMessageInquiryAdapter;
import com.bw.message.presenter.MyUserInquiryMessage;
import com.dingtao.common.bean.MyUserMessage;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import health.wd.com.open_mymessage.R;
import health.wd.com.open_mymessage.R2;

public class InquiryActivity extends WDActivity {


    @BindView(R2.id.inquiryfinish)
    ImageView inquiryfinish;
    @BindView(R2.id.inquiryrecycler)
    RecyclerView inquiryrecycler;
    private MyUserInquiryMessage myUserInquiryMessage;
    private MyUserMessageInquiryAdapter myUserMessageInquiryAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inquiry;
    }

    @Override
    protected void initView() {
        inquiryfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //接口
        myUserInquiryMessage = new MyUserInquiryMessage(new getdata());
        //适配器
        myUserMessageInquiryAdapter = new MyUserMessageInquiryAdapter(this);
        inquiryrecycler.setAdapter(myUserMessageInquiryAdapter);
        inquiryrecycler.setLayoutManager
                (new LinearLayoutManager(InquiryActivity.this, LinearLayoutManager.VERTICAL, false));


    }

    @Override
    protected void destoryData() {

    }
    //**************************
    class getdata implements DataCall<List<MyUserMessage>>{

        @Override
        public void success(List<MyUserMessage> data, Object... args) {
            myUserMessageInquiryAdapter.clear();
            myUserMessageInquiryAdapter.add(data);
            myUserMessageInquiryAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> intt = new LoginDaoUtil().intt(InquiryActivity.this);
        myUserInquiryMessage.reqeust(intt.get(0), intt.get(1), 1, 10);
    }
}
