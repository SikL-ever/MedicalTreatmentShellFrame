package com.wd.MyHome.childactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.MyUser.MyUserLookNewinquiryBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.snackbar.Snackbar;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.presenter.MyUserEndNewInquiryPresenter;
import com.wd.MyHome.presenter.MyUserLookNewInquiry;
import com.wd.MyHome.util.TopView;
import com.wd.health.util.MyDialog;
import com.wd.im.activity.ConsultChatMainActivity;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;

@Route(path = Constant.ACTIVITY_MODE_MYUSERNEW_INQUIRY)
public class MyUserNewInquiryActivity extends WDActivity {


    @BindView(R2.id.myusernewtop)
    TopView myusernewtop;
    @BindView(R2.id.myusernewimage)
    SimpleDraweeView myusernewimage;
    @BindView(R2.id.myusernewname)
    TextView myusernewname;
    @BindView(R2.id.myusernewpost)
    TextView myusernewpost;
    @BindView(R2.id.myusernewoffice)
    TextView myusernewoffice;
    @BindView(R2.id.myusernewtime)
    TextView myusernewtime;
    @BindView(R2.id.myusernewcontinue)
    Button myusernewcontinue;
    @BindView(R2.id.myusernewend)
    Button myusernewend;
    @BindView(R2.id.myusernewn_layout)
    RelativeLayout myusernewnLayout;
    @BindView(R2.id.myuserlayouttwo)
    RelativeLayout myusernewnLayouttwo;
    private MyUserLookNewInquiry myUserLookNewInquiry;
    private MyUserEndNewInquiryPresenter myUserEndNewInquiryPresenter;
    private List<String> intt;
    private int id;//问诊id
    private int doctorIdb;
    private String username,name;//医生的username
    private MyDialog dialog;//弹框

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_new_inquiry;
    }

    @Override
    protected void initView() {
        //创建p层
        myUserLookNewInquiry = new MyUserLookNewInquiry(new getdta());
        myUserEndNewInquiryPresenter = new MyUserEndNewInquiryPresenter(new endgetdta());
        myusernewtop.setTitle("当前问诊");
        //结束问诊
        myusernewend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出altdio
                View view=View.inflate(MyUserNewInquiryActivity.this, com.wd.health.R.layout.videodialong_item,null);
                dialog = new MyDialog(MyUserNewInquiryActivity.this, 200, 100, view, com.wd.health.R.style.dialog);
                dialog.show();
                final TextView cancel =
                        (TextView) view.findViewById(com.wd.health.R.id.cancel);
                final TextView confirm =
                        (TextView)view.findViewById(com.wd.health.R.id.confirm);
                final TextView text =
                        (TextView)view.findViewById(com.wd.health.R.id.textView10);
                text.setText("是否结束问诊?");
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myUserEndNewInquiryPresenter.reqeust(intt.get(0), intt.get(1), id);
                    }
                });
            }
        });
        //继续问诊
        myusernewcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到消息页面穿username过去
                Intent intent = new Intent(MyUserNewInquiryActivity.this, ConsultChatMainActivity.class);
                intent.putExtra("id",username);
                intent.putExtra("name",name);
                intent.putExtra("type",2);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void destoryData() {

    }


    //*******************************************************************
    //我的当前咨询
    class getdta implements DataCall<MyUserLookNewinquiryBean> {
        @Override
        public void success(MyUserLookNewinquiryBean data, Object... args) {
            if (data == null) {
                myusernewnLayout.setVisibility(View.GONE);
                myusernewnLayouttwo.setVisibility(View.VISIBLE);
            }else{
                username=data.userName;
                name=data.doctorName;
                myusernewnLayout.setVisibility(View.VISIBLE);
                myusernewnLayouttwo.setVisibility(View.GONE);
                id = data.recordId;
                doctorIdb=data.doctorId;
                myusernewimage.setImageURI(data.imagePic);//加载图片
                myusernewname.setText(data.doctorName);//名字
                myusernewpost.setText(data.jobTitle);//职位
                myusernewoffice.setText(data.department);//科室
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(data.inquiryTime);
                myusernewtime.setText("问诊时间" + dateString);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    //结束问诊
    class endgetdta implements DataCall {
        @Override
        public void success(Object data, Object... args) {
            dialog.dismiss();
            Snackbar.make(myusernewnLayout,"结束成功",Snackbar.LENGTH_SHORT).show();
            //跳转到评价页面
            Intent intent = new Intent(MyUserNewInquiryActivity.this,MyUserEvaluateActivity.class);
            intent.putExtra("aid",id);//问诊
            intent.putExtra("did",doctorIdb);//医生
            startActivity(intent);
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    //*******************************************************************
    @Override
    protected void onResume() {
        super.onResume();
        intt = new LoginDaoUtil().intt(MyUserNewInquiryActivity.this);
        myUserLookNewInquiry.reqeust(intt.get(0), intt.get(1));
    }

}
