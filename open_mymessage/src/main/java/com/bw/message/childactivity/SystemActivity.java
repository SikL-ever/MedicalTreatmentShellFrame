package com.bw.message.childactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bw.message.adapter.MyUserMessageSystemAdapter;
import com.bw.message.presenter.MyUserSystemMessage;
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

public class SystemActivity extends WDActivity {


    @BindView(R2.id.systemrecycler)
    RecyclerView systemrecycler;
    @BindView(R2.id.systemfinish)
    ImageView systemfinish;
    private MyUserSystemMessage myUserSystemMessage;
    private MyUserMessageSystemAdapter myUserMessageSystemAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system;
    }

    @Override
    protected void initView() {
        systemfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myUserSystemMessage = new MyUserSystemMessage(new getdata());
        myUserMessageSystemAdapter = new MyUserMessageSystemAdapter(this);
        systemrecycler.setAdapter(myUserMessageSystemAdapter);
        systemrecycler.setLayoutManager
                (new LinearLayoutManager(SystemActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void destoryData() {

    }


    //-----------------------------------------------
    class getdata implements DataCall<List<MyUserMessage>> {

        @Override
        public void success(List<MyUserMessage> data, Object... args) {
            myUserMessageSystemAdapter.clear();
            myUserMessageSystemAdapter.add(data);
            myUserMessageSystemAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    //请求接
    @Override
    protected void onResume() {
        super.onResume();
        List<String> intt = new LoginDaoUtil().intt(SystemActivity.this);
        myUserSystemMessage.reqeust(intt.get(0), intt.get(1), 1, 10);
    }
}
