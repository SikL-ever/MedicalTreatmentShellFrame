package com.wd.health.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.homepage.YsxqBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.presenter.homepagepresenter.GzysPresenter;
import com.wd.health.adapter.homepageadapter.PjAdapter;
import com.wd.health.presenter.homepagepresenter.QianBaoPresenter;
import com.wd.health.presenter.homepagepresenter.QxysPresenter;
import com.wd.health.presenter.homepagepresenter.YsxqPresenter;
import com.wd.health.util.CommomDialog;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class YsxqActivity extends AppCompatActivity {

    @BindView(R2.id.c_images)
    ImageView cImages;
    @BindView(R2.id.c_edit12)
    TextView cEdit12;
    @BindView(R2.id.c_xiaoxi_121)
    CheckBox cXiaoxi121;
    @BindView(R2.id.c_aaaa)
    RelativeLayout cAaaa;
    @BindView(R2.id.c_img)
    SimpleDraweeView cImg;
    @BindView(R2.id.c_gz)
    ImageView cGz;
    @BindView(R2.id.c_name)
    TextView cName;
    @BindView(R2.id.c_guan)
    TextView cGuan;
    @BindView(R2.id.c_yiyuan)
    TextView cYiyuan;
    @BindView(R2.id.c_hpl)
    TextView cHpl;
    @BindView(R2.id.c_hzs)
    TextView cHzs;
    @BindView(R2.id.vv)
    View vv;
    @BindView(R2.id.c_sdlw)
    TextView cSdlw;
/*    @BindView(R2.id.c_flower)
    ImageView cFlower;
    @BindView(R2.id.c_hua)
    TextView cHua;
    @BindView(R2.id.c_cer)
    ImageView cCer;
    @BindView(R2.id.c_ben)
    TextView cBen;
    @BindView(R2.id.c_cup)
    ImageView cCup;
    @BindView(R2.id.c_bei)
    TextView cBei;*/
    @BindView(R2.id.c_bbbb)
    RelativeLayout cBbbb;
    @BindView(R2.id.vv1)
    View vv1;
    @BindView(R2.id.grjj)
    TextView grjj;
    @BindView(R2.id.c_grjj)
    TextView cGrjj;
    @BindView(R2.id.vv2)
    View vv2;
    @BindView(R2.id.scly)
    TextView scly;
    @BindView(R2.id.c_scly)
    TextView cScly;
    @BindView(R2.id.vv3)
    View vv3;
    @BindView(R2.id.yhpj)
    TextView yhpj;
    @BindView(R2.id.c_plgs)
    TextView cPlgs;
    @BindView(R2.id.c_recycler_pj)
    RecyclerView cRecyclerPj;
    @BindView(R2.id.c_cccc)
    RelativeLayout cCccc;
    @BindView(R2.id.c_hb)
    TextView cHb;
    @BindView(R2.id.c_ljzx)
    TextView cLjzx;
    @BindView(R2.id.c_ds)
    RecyclerView c_ds;
    @BindView(R2.id.ckgd)
    TextView ckgd;
    private String userId;
    private String sesssionId;
    private int a;
    private int followFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yszq_item_xq);
        ButterKnife.bind(this);
        cImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int doctorId = getIntent().getIntExtra("doctorId", 0);
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(this);
        userId = intt.get(0);
        sesssionId = intt.get(1);
        YsxqPresenter ysxqPresenter = new YsxqPresenter(new YsxqShow());
        ysxqPresenter.reqeust(userId, sesssionId,doctorId);
        cGz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (followFlag ==2){
                    followFlag=1;
                    GzysPresenter gzysPresenter = new GzysPresenter(new DataCall<Result>() {
                        @Override
                        public void success(Result data, Object... args) {
                            Glide.with(YsxqActivity.this).load(R.drawable.common_icon_attention_large_s).into(cGz);
                            Toast.makeText(YsxqActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void fail(ApiException data, Object... args) {

                        }
                    });
                    gzysPresenter.reqeust(userId,sesssionId,doctorId);
                }else{
                    followFlag=2;
                    QxysPresenter qxysPresenter = new QxysPresenter(new DataCall<Result>() {
                        @Override
                        public void success(Result data, Object... args) {
                            Glide.with(YsxqActivity.this).load(R.drawable.common_icon_attention_large_n).into(cGz);
                            Toast.makeText(YsxqActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void fail(ApiException data, Object... args) {

                        }
                    });
                    qxysPresenter.reqeust(userId,sesssionId,doctorId);
                }
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(YsxqActivity.this,3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        c_ds.setLayoutManager(layoutManager);
        cRecyclerPj.setLayoutManager(new LinearLayoutManager(this));
        ckgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YsxqActivity.this,GdplActivity.class);
                intent.putExtra("doctorId1",doctorId);
                startActivity(intent);
            }
        });
    }

    private class YsxqShow implements DataCall<YsxqBean> {
        @Override
        public void success(YsxqBean data, Object... args) {
            RoundedCorners roundedCorners= new RoundedCorners(8);
            RequestOptions options=RequestOptions.bitmapTransform(roundedCorners);
            if(data.getImagePic()==null){
                Glide.with(YsxqActivity.this).load(R.drawable.system_image7).apply(options).into(cImg);
                //cImg.setImageURI();
            }else{
             //   Glide.with(YsxqActivity.this).load(data.getImagePic()).apply(options).into(cImg);
                cImg.setImageURI(data.getImagePic());
            }
            followFlag = data.getFollowFlag();
            if (followFlag ==1){
                Glide.with(YsxqActivity.this).load(R.drawable.common_icon_attention_large_s).into(cGz);
            }else{
                Glide.with(YsxqActivity.this).load(R.drawable.common_icon_attention_large_n).into(cGz);
            }
            cName.setText(data.getDoctorName());
            cGuan.setText(data.getJobTitle());
            cYiyuan.setText(data.getInauguralHospital());
            cHpl.setText("好评率  "+data.getPraise()+"%");
            cHzs.setText("服务患者数  "+data.getServerNum());
            cGrjj.setText(data.getPersonalProfile());
            cScly.setText(data.getGoodField());
            cPlgs.setText("("+data.getCommentNum()+"条评论)");
            cHb.setText(data.getServicePrice()+"H币/次");
            a = data.getServicePrice();
            cLjzx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    QianBaoPresenter qianBaoPresenter = new QianBaoPresenter(new DataCall<Double>() {
                        @Override
                        public void success(Double data, Object... args) {
                            if (data>=a){
                                CommomDialog commomDialog = new CommomDialog(YsxqActivity.this, R.style.dialog_xz, "本次咨询将扣除"+a+"H币！", new CommomDialog.OnCloseListener() {
                                    @Override
                                    public void onClick(Dialog dialog, boolean confirm) {
                                        if (confirm==true){
                                            Toast.makeText(YsxqActivity.this, "跳转医生即时通讯对话", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(YsxqActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                        }
                                        dialog.dismiss();
                                    }
                                });
                                commomDialog.setPositiveButton("去咨询").show();
                            }else{
                                CommomDialog commomDialog = new CommomDialog(YsxqActivity.this, R.style.dialog_xz, "H币不足"+a+"，充值再来吧！", new CommomDialog.OnCloseListener() {
                                    @Override
                                    public void onClick(Dialog dialog, boolean confirm) {
                                        if (confirm==true){
                                            Toast.makeText(YsxqActivity.this, "跳转充值页面", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(YsxqActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                        }
                                        dialog.dismiss();
                                    }
                                });
                                commomDialog.setPositiveButton("去充值").show();
                            }
                        }

                        @Override
                        public void fail(ApiException data, Object... args) {
                            Log.e("aaaaaaaaaaaaa+++",data+"");
                        }
                    });
                    qianBaoPresenter.reqeust(userId,sesssionId);//请求用户余额
                }
            });

            //礼物
           /* DsAdapter dsAdapter = new DsAdapter(YsxqActivity.this,data.getCommentList());
            c_ds.setAdapter(dsAdapter);*/
            //评论
            if (data.getCommentNum()>3){
                ckgd.setVisibility(View.VISIBLE);
            }else{
                ckgd.setVisibility(View.GONE);
            }
            PjAdapter pjAdapter = new PjAdapter(YsxqActivity.this,data.getCommentList());
            cRecyclerPj.setAdapter(pjAdapter);
            pjAdapter.notifyDataSetChanged();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
