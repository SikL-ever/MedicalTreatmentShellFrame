package com.wd.health.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingtao.common.bean.homepage.YsxqBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.homepageadapter.PjAdapter;
import com.wd.health.adapter.homepageadapter.PjgdAdapter;
import com.wd.health.presenter.homepagepresenter.YsxqPresenter;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GdplActivity extends AppCompatActivity {

    @BindView(R2.id.pllb)
    TextView pllb;
    @BindView(R2.id.c_pllb)
    ImageView cPllb;
    @BindView(R2.id.frecycle)
    RecyclerView frecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdpl);
        ButterKnife.bind(this);
        int doctorId = getIntent().getIntExtra("doctorId1", 0);
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(this);
        String userId = intt.get(0);
        String sesssionId = intt.get(1);
        YsxqPresenter ysxqPresenter = new YsxqPresenter(new YsxqShow());
        ysxqPresenter.reqeust(userId, sesssionId,doctorId);
        frecycle.setLayoutManager(new LinearLayoutManager(this));
        cPllb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private class YsxqShow implements DataCall<YsxqBean> {
        @Override
        public void success(YsxqBean data, Object... args) {
            PjgdAdapter pjAdapter = new PjgdAdapter(GdplActivity.this,data.getCommentList());
            frecycle.setAdapter(pjAdapter);
            pjAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
