package com.wd.MyHome.childactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dingtao.common.bean.MyUser.MyUserSuggestBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.MyUserSuggestAdapter;
import com.wd.MyHome.presenter.MyUserSuggestPresenter;
import com.wd.MyHome.util.TopView;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyUserSuggestActivity extends WDActivity {


    @BindView(R2.id.myusersuggesttop)
    TopView myusersuggesttop;
    @BindView(R2.id.myusersuggestrecycler)
    RecyclerView myusersuggestrecycler;
    @BindView(R2.id.myusersuggestno)
    RelativeLayout myusersuggestno;
    private MyUserSuggestPresenter myUserSuggestPresenter;
    private MyUserSuggestAdapter myUserSuggestAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_suggest;
    }

    @Override
    protected void initView() {
        //设置标题
        myusersuggesttop.setTitle("被采纳的建议");
        //请求数据的p层
        myUserSuggestPresenter = new MyUserSuggestPresenter(new getdata());
        //创建适配器
        myUserSuggestAdapter = new MyUserSuggestAdapter(this);
        myusersuggestrecycler.setAdapter(myUserSuggestAdapter);
        myusersuggestrecycler.setLayoutManager
                (new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void destoryData() {

    }
    //获取焦点

    @Override
    protected void onResume() {
        super.onResume();
        List<String> intt = new LoginDaoUtil().intt(MyUserSuggestActivity.this);
        if (intt == null) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }else{
            myUserSuggestPresenter.reqeust(intt.get(0),intt.get(1),1,10);

        }
    }

    //请求我的被采纳的建议的数据
    class getdata implements DataCall<List<MyUserSuggestBean>>{
        @Override
        public void success(List<MyUserSuggestBean> data, Object... args) {
            //判断
            if (data.size()==0){
                myusersuggestno.setVisibility(View.VISIBLE);
                myusersuggestrecycler.setVisibility(View.GONE);
            }else{
                myusersuggestrecycler.setVisibility(View.VISIBLE);
                myusersuggestno.setVisibility(View.GONE);
                myUserSuggestAdapter.clear();
                myUserSuggestAdapter.addList(data);
            }

        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
}
