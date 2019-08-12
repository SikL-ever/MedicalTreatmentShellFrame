package com.wd.MyHome.childactivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dingtao.common.bean.wardBean.MyDoctorBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.MyDoctorAdapater;
import com.wd.MyHome.presenter.MyDoctorPresenter;

import java.util.List;

public class MyInteresterActivity extends AppCompatActivity {

    @BindView(R2.id.interest_back)
    ImageView interestBack;
    @BindView(R2.id.tu)
    ImageView tu;
    @BindView(R2.id.gones)
    RelativeLayout gones;
    @BindView(R2.id.interest_recycler)
    RecyclerView interestRecycler;
    private MyDoctorAdapater myDoctorAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_interester);
        ButterKnife.bind(this);
        MyDoctorPresenter myDoctorPresenter = new MyDoctorPresenter(new mydoctor());
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(MyInteresterActivity.this);
        myDoctorPresenter.reqeust(intt.get(0), intt.get(1), 1, 5);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        myDoctorAdapater = new MyDoctorAdapater(this);
        interestRecycler.setLayoutManager(linearLayoutManager);
        interestRecycler.setAdapter(myDoctorAdapater);
        interestBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        interestRecycler.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
//        interestRecycler.setItemAnimator(new DefaultItemAnimator());

    }


    class mydoctor implements DataCall<List<MyDoctorBean>> {
        @Override
        public void success(List<MyDoctorBean> data, Object... args) {
            if(data.size()==0){
                gones.setVisibility(View.VISIBLE);
            }else{
                gones.setVisibility(View.GONE);
            }
            myDoctorAdapater.setdoctor(data);
            myDoctorAdapater.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
