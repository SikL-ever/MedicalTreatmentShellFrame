package com.bw.message.childactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bw.message.adapter.MyUserMessageHAdapter;
import com.bw.message.presenter.MyUserHPresenter;
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

public class HActivity extends WDActivity {


    @BindView(R2.id.mymessagehrecycler)
    RecyclerView mymessagehrecycler;
    @BindView(R2.id.hfinish)
    ImageView hfinish;
    private MyUserHPresenter myUserHPresenter;
    private MyUserMessageHAdapter myUserMessageHAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_h;
    }

    @Override
    protected void initView() {
        hfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //请求p
        myUserHPresenter = new MyUserHPresenter(new getdata());
        //创建适配器
        myUserMessageHAdapter = new MyUserMessageHAdapter(HActivity.this);
        mymessagehrecycler.setAdapter(myUserMessageHAdapter);
        mymessagehrecycler.setLayoutManager
                (new LinearLayoutManager(HActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void destoryData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> intt = new LoginDaoUtil().intt(HActivity.this);
        myUserHPresenter.reqeust(intt.get(0), intt.get(1), 1, 20);
    }

    //-----------------------------------------
    class getdata implements DataCall<List<MyUserMessage>> {
        @Override
        public void success(List<MyUserMessage> data, Object... args) {
            myUserMessageHAdapter.clear();
            myUserMessageHAdapter.add(data);
            myUserMessageHAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
}
