package com.wd.MyHome.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.presenter.XgxbPresenter;
import com.wd.MyHome.util.TopView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class XbActivity extends AppCompatActivity {

    @BindView(R2.id.xb_top)
    TopView xbTop;
    @BindView(R2.id.xb_wc)
    TextView xbWc;
    @BindView(R2.id.nan_1)
    ImageView nan1;
    @BindView(R2.id.nan_a_1)
    ImageView nanA1;
    @BindView(R2.id.ta)
    RelativeLayout ta;
    @BindView(R2.id.q1)
    TextView q1;
    @BindView(R2.id.nv_1)
    ImageView nv1;
    @BindView(R2.id.tb)
    RelativeLayout tb;
    @BindView(R2.id.nv_2)
    ImageView nv2;
    @BindView(R2.id.tc)
    RelativeLayout tc;
    @BindView(R2.id.nan_2)
    ImageView nan2;
    @BindView(R2.id.td)
    RelativeLayout td;
    private List<String> intt;
    private String userId;
    private String sessionId;
    private int a = 0;
    private int xb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xb);
        ButterKnife.bind(this);
        xbTop.setTitle("选择性别");
        intt = new LoginDaoUtil().intt(this);
        userId = intt.get(0);
        sessionId = intt.get(1);
        xb = getIntent().getIntExtra("xb", 0);
        if (xb==1){
            nanA1.setVisibility(View.VISIBLE);
            nan2.setVisibility(View.VISIBLE);
            nv1.setVisibility(View.GONE);
            nv2.setVisibility(View.GONE);
        }
        if (xb==2){
            nanA1.setVisibility(View.GONE);
            nan2.setVisibility(View.GONE);
            nv1.setVisibility(View.VISIBLE);
            nv2.setVisibility(View.VISIBLE);
        }
        td.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=1;

                nanA1.setVisibility(View.VISIBLE);
                nan2.setVisibility(View.VISIBLE);
                nv1.setVisibility(View.GONE);
                nv2.setVisibility(View.GONE);
                xbWc.setBackgroundColor(Color.parseColor("#619fe5"));
            }
        });
        ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=1;

                nanA1.setVisibility(View.VISIBLE);
                nan2.setVisibility(View.VISIBLE);
                nv1.setVisibility(View.GONE);
                nv2.setVisibility(View.GONE);
                xbWc.setBackgroundColor(Color.parseColor("#619fe5"));
            }
        });
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=2;

                nanA1.setVisibility(View.GONE);
                nan2.setVisibility(View.GONE);
                nv1.setVisibility(View.VISIBLE);
                nv2.setVisibility(View.VISIBLE);
                xbWc.setBackgroundColor(Color.parseColor("#619fe5"));
            }
        });
        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=2;
                nanA1.setVisibility(View.GONE);
                nan2.setVisibility(View.GONE);
                nv1.setVisibility(View.VISIBLE);
                nv2.setVisibility(View.VISIBLE);
                xbWc.setBackgroundColor(Color.parseColor("#619fe5"));
            }
        });
        xbWc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAa(a);
                xbWc.setBackgroundColor(Color.parseColor("#B0CFF2"));
            }
        });

    }

    private void onAa(int a) {
        XgxbPresenter xgxbPresenter = new XgxbPresenter(new DataCall() {
            @Override
            public void success(Object data, Object... args) {
                finish();
            }

            @Override
            public void fail(ApiException data, Object... args) {

            }
        });
        xgxbPresenter.reqeust(userId,sessionId,a);
    }

}
