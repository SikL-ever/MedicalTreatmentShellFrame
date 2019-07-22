package com.wd.health.activity.wardActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dingtao.common.bean.wardBean.TaFaBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.wardmateadapter.TaFaAdapater;
import com.wd.health.presenter.wardmatepresenter.TaFaPresenter;
import com.wd.health.presenter.wardmatepresenter.TabPresenter;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaWardActivity extends AppCompatActivity {


    private TaFaAdapater taFaAdapater;
    private ImageView beijing;
    private ImageView imageView;
    private TextView head_nam;
    private RecyclerView recyclerView;
    private TaFaPresenter taFaPresenter;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ta_ward);
        ButterKnife.bind(this);
        beijing = findViewById(R.id.beijing);
        imageView = findViewById(R.id.image_hea);
        head_nam = findViewById(R.id.head_nam);
        recyclerView = findViewById(R.id.recyclerview_ta);


        taFaPresenter = new TaFaPresenter(new tafa());
        taFaPresenter.reqeust(1,1,10);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        taFaAdapater = new TaFaAdapater(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(taFaAdapater);



    }

    class tafa implements DataCall<List<TaFaBean>> {
        @Override
        public void success(List<TaFaBean> data, Object... args) {
            taFaAdapater.setta(data);
            taFaAdapater.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
