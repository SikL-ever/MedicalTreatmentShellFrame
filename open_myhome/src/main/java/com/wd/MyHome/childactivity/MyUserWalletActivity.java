package com.wd.MyHome.childactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.dingtao.common.bean.MyUser.MyUserWalletLookBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.MyUserWalletLookAdapter;
import com.wd.MyHome.presenter.MyUserWalletLookPresenter;
import com.wd.MyHome.util.TopView;
import com.wd.health.presenter.videopresenter.VideoGetPricePresenter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyUserWalletActivity extends WDActivity {

    @BindView(R2.id.myuserwallettop)
    TopView myuserwallettop;
    @BindView(R2.id.myuserwalletprice)
    TextView myuserwalletprice;
    @BindView(R2.id.walletti)
    Button walletti;
    @BindView(R2.id.walletchong)
    Button walletchong;
    @BindView(R2.id.myuserwalletreycler)
    RecyclerView myuserwalletreycler;
    private VideoGetPricePresenter buyVideoPresenter;
    private MyUserWalletLookPresenter myUserWalletLookPresenter;
    private MyUserWalletLookAdapter myUserWalletLookAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_wallet;
    }

    @Override
    protected void initView() {
        //设置标题
        myuserwallettop.setTitle("我的钱包");
        //创建p层
        buyVideoPresenter = new VideoGetPricePresenter(new videogetprice());
        myUserWalletLookPresenter = new MyUserWalletLookPresenter(new mywalletdata());
        //创建适配器
        myUserWalletLookAdapter = new MyUserWalletLookAdapter(MyUserWalletActivity.this);
        myuserwalletreycler.setAdapter(myUserWalletLookAdapter);
        myuserwalletreycler.setLayoutManager
                (new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void destoryData() {

    }
    //在获取焦点出进行获取数据
    @Override
    protected void onResume() {
        super.onResume();
        List<String> intt = new LoginDaoUtil().intt(this);
        if (intt == null) {

        }else{
            buyVideoPresenter.reqeust(intt.get(0),intt.get(1));//请求余额
            myUserWalletLookPresenter.reqeust(intt.get(0),intt.get(1),1,10);//请求记录
        }
    }
    //请求下来的我的余额
    class videogetprice implements DataCall<Double> {
        @Override
        public void success(Double data, Object... args) {
            myuserwalletprice.setText(data+"");
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //请求消费记录返回的数据
    class mywalletdata implements DataCall<List<MyUserWalletLookBean>>{
        @Override
        public void success(List<MyUserWalletLookBean> data, Object... args) {
            //设置到适配器上
            myUserWalletLookAdapter.clear();
            myUserWalletLookAdapter.addList(data);

        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
}
