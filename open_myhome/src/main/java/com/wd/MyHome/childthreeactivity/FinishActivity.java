package com.wd.MyHome.childthreeactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dingtao.common.bean.MyUser.GiftBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.google.android.material.snackbar.Snackbar;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.MyUserGiftAdapter;
import com.wd.MyHome.presenter.MyUserGiftPresenter;
import com.wd.MyHome.presenter.MyUserGiveGiftPresenter;
import com.wd.MyHome.util.TopView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FinishActivity extends WDActivity {


    @BindView(R2.id.accomptop)
    TopView accomptop;
    @BindView(R2.id.accomrecycler)
    RecyclerView accomrecycler;
    @BindView(R2.id.accompfinish)
    Button accompfinish;
    @BindView(R2.id.finishlayout)
    LinearLayout finishlayout;
    private MyUserGiftAdapter myUserGiftAdapter;
    private MyUserGiftPresenter myUserGiftPresenter;
    private MyUserGiveGiftPresenter myUserGiveGiftPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_finish;
    }

    @Override
    protected void initView() {
        accomptop.setTitle("评论成功");
        //创建p层
        myUserGiftPresenter = new MyUserGiftPresenter(new getdata());//获取礼物
        myUserGiveGiftPresenter = new MyUserGiveGiftPresenter(new getgivedta());//送礼物
        //接受到的问诊id
        final int id1 = getIntent().getIntExtra("id", 0);
        //创建适配器
        myUserGiftAdapter = new MyUserGiftAdapter(this);
        accomrecycler.setAdapter(myUserGiftAdapter);
        accomrecycler.setLayoutManager
                (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        myUserGiftAdapter.setClackBackData(new MyUserGiftAdapter.ClackBackData() {
            @Override
            public void getdata(int id) {
                List<String> intt = new LoginDaoUtil().intt(FinishActivity.this);
                myUserGiveGiftPresenter.reqeust(intt.get(0), intt.get(1), id1, id);
            }
        });
        //返回
        accompfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void destoryData() {

    }
    //****************************************
    //礼物清单
    class getdata implements DataCall<List<GiftBean>> {
        @Override
        public void success(List<GiftBean> data, Object... args) {
            myUserGiftAdapter.clear();
            myUserGiftAdapter.add(data);
            myUserGiftAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    //送礼物
    class getgivedta implements DataCall {
        @Override
        public void success(Object data, Object... args) {
            Snackbar.make(finishlayout,"赠送成功",Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    //****************************************
    @Override
    protected void onResume() {
        super.onResume();
        myUserGiftPresenter.reqeust();
    }
}
