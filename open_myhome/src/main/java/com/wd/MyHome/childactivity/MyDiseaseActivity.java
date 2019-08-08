package com.wd.MyHome.childactivity;

import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dingtao.common.bean.wardBean.MyDiseaseBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.adapter.MyDiseaseAdapater;
import com.wd.MyHome.presenter.MyDiseasePresenter;

import java.util.List;

public class MyDiseaseActivity extends AppCompatActivity {
    private ImageView desease_back;
    private RecyclerView desease_recycler;
    private MyDiseasePresenter myDiseasePresenter;
    private MyDiseaseAdapater myDiseaseAdapater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_disease);
        desease_back = findViewById(R.id.desease_back);
        desease_recycler = findViewById(R.id.desease_recycler);

        myDiseasePresenter = new MyDiseasePresenter(new disease());
        LoginDaoUtil loginDaoUtil=new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(MyDiseaseActivity.this);
        myDiseasePresenter.reqeust(intt.get(0),intt.get(1),1,5);
        myDiseaseAdapater = new MyDiseaseAdapater(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        desease_recycler.setLayoutManager(linearLayoutManager);
        desease_recycler.setAdapter(myDiseaseAdapater);
        desease_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    class disease implements DataCall<List<MyDiseaseBean>> {
        @Override
        public void success(List<MyDiseaseBean> data, Object... args) {
            myDiseaseAdapater.setdisease(data);
            myDiseaseAdapater.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {


        }

    }
}
