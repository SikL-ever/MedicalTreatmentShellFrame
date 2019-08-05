package com.wd.MyHome.childactivity;

import android.os.Bundle;

import com.dingtao.common.bean.MyUser.MyUserHistoryBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.MyUserHistoryAdapter;
import com.wd.MyHome.presenter.MyUserHistoryPresenter;
import com.wd.MyHome.util.TopView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyUserHistoryActivity extends WDActivity {


    @BindView(R2.id.myuserhistorytop)
    TopView myuserhistorytop;
    @BindView(R2.id.myuserhistoryrecycler)
    RecyclerView myuserhistoryrecycler;
    private MyUserHistoryPresenter myUserHistoryPresenter;
    private MyUserHistoryAdapter myUserHistoryAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_history;
    }
    @Override
    protected void initView() {
        myuserhistorytop.setTitle("历史问诊");
        //创建p层
        myUserHistoryPresenter = new MyUserHistoryPresenter(new getdata());
        //创建适配器
        myUserHistoryAdapter = new MyUserHistoryAdapter(this);
        myuserhistoryrecycler.setAdapter(myUserHistoryAdapter);
        myuserhistoryrecycler.setLayoutManager
                (new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    @Override
    protected void destoryData() {

    }
    //******************************************************************
    class getdata implements DataCall<List<MyUserHistoryBean>>{
        @Override
        public void success(List<MyUserHistoryBean> data, Object... args) {
            myUserHistoryAdapter.clear();
            myUserHistoryAdapter.add(data);
            myUserHistoryAdapter.notifyDataSetChanged();
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //******************************************************************
    @Override
    protected void onResume() {
        super.onResume();
        List<String> intt = new LoginDaoUtil().intt(this);
        myUserHistoryPresenter.reqeust(intt.get(0),intt.get(1),1,10);
    }
}
