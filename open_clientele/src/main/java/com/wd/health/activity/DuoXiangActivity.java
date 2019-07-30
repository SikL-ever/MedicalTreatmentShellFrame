package com.wd.health.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.homepage.DuotiaomuBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.homepageadapter.DuotiaomuAdapter;
import com.wd.health.fragment.HomePagerFragement;
import com.wd.health.presenter.homepagepresenter.DuotiaomuPresenter;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DuoXiangActivity extends AppCompatActivity {

    @BindView(R2.id.gengduo_headimg)
    ImageView gengduoHeadimg;
    @BindView(R2.id.gengduo_name)
    TextView gengduoName;
    @BindView(R2.id.gengduo_tongzhimg)
    CheckBox gengduoTongzhimg;
    @BindView(R2.id.r1)
    RelativeLayout r1;
    @BindView(R2.id.gengduo_recycler)
    RecyclerView gengduoRecycler;
    private DuotiaomuPresenter duotiaomuPresenter;
    private DuotiaomuAdapter duotiaomuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duo_xiang);
        ButterKnife.bind(this);
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(this);
        if (intt!=null){
            String yhtx = intt.get(2);
            Glide.with(this).load(yhtx).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(gengduoHeadimg);
        }else{
            Glide.with(this).load(R.drawable.register_icon_invitatiion_code_n).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(gengduoHeadimg);
        }
        String asdf = getIntent().getStringExtra("asdf");
        gengduoName.setText(asdf);
        gengduoRecycler.setLayoutManager(new LinearLayoutManager(DuoXiangActivity.this,RecyclerView.VERTICAL,false));
        duotiaomuAdapter = new DuotiaomuAdapter(DuoXiangActivity.this);
        gengduoRecycler.setAdapter(duotiaomuAdapter);
        duotiaomuPresenter = new DuotiaomuPresenter(new Dtm());
        duotiaomuPresenter.reqeust(1,1,10);
    }
    private class Dtm implements DataCall<List<DuotiaomuBean>> {
        @Override
        public void success(List<DuotiaomuBean> data, Object... args) {
            duotiaomuAdapter.setList(data);
            duotiaomuAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
