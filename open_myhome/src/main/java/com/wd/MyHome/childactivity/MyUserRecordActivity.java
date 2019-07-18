package com.wd.MyHome.childactivity;

import android.os.Bundle;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.presenter.UserRecordPresenter;
import com.wd.MyHome.util.TopView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyUserRecordActivity extends WDActivity {

    @BindView(R2.id.myuserrecordtop)
    TopView myuserrecordtop;
    private UserRecordPresenter userRecordPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_record;
    }

    @Override
    protected void initView() {
        //设置标题
        myuserrecordtop.setTitle("我的档案");
        //p层
       // userRecordPresenter = new UserRecordPresenter();
    }
    //返回的数据
    //class getuserrecord implements DataCall<>

    @Override
    protected void destoryData() {

    }
}
