package com.wd.MyHome.childactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.dingtao.common.bean.wardBean.Ping_lie_Bean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.adapter.MyDiseasechildAdapater;
import com.wd.MyHome.presenter.PingliesPresenter;

import java.util.List;

public class MyChildDiseaseActivity extends AppCompatActivity {

    @BindView(R2.id.disease_back)
    ImageView diseaseBack;
    @BindView(R2.id.disease_recycler)
    RecyclerView diseaseRecycler;
    private MyDiseasechildAdapater myDiseasechildAdapater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_child_disease);
        ButterKnife.bind(this);
        PingliesPresenter pingliesPresenter=new PingliesPresenter(new ping());
        LoginDaoUtil loginDaoUtil=new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(MyChildDiseaseActivity.this);
        pingliesPresenter.reqeust(intt.get(0),intt.get(1),16,1,5);
        myDiseasechildAdapater = new MyDiseasechildAdapater(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        diseaseRecycler.setLayoutManager(linearLayoutManager);
        diseaseRecycler.setAdapter(myDiseasechildAdapater);

        diseaseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    class ping implements DataCall<List<Ping_lie_Bean>> {
        @Override
        public void success(List<Ping_lie_Bean> data, Object... args) {
            myDiseasechildAdapater.setchild(data);
            myDiseasechildAdapater.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
