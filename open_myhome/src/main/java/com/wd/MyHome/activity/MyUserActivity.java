package com.wd.MyHome.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.childactivity.MyUserCollectActivity;
import com.wd.MyHome.childactivity.MyUserRecordActivity;
import com.wd.MyHome.childactivity.MyUserSetActivity;
import com.wd.MyHome.childactivity.MyUserSuggestActivity;
import com.wd.MyHome.childactivity.MyUserWalletActivity;
import com.wd.MyHome.presenter.UserSignPresenter;

import java.util.List;

import butterknife.BindView;

@Route(path = Constant.ACTIVITY_LOGIN_MYUSERACTIVITY)
public class MyUserActivity extends WDActivity {
    @BindView(R2.id.myuserfinish)
    ImageView myuserfinish;
    @BindView(R2.id.myusermessage)
    ImageView myusermessage;
    @BindView(R2.id.myuserheadportrait)
    ImageView myuserheadportrait;
    @BindView(R2.id.myusername)
    TextView myusername;
    @BindView(R2.id.myuserQiandao)
    Button myuserQiandao;
    @BindView(R2.id.myusernew)
    RelativeLayout myusernew;
    @BindView(R2.id.myuserhistory)
    RelativeLayout myuserhistory;
    @BindView(R2.id.myuserrecord)
    LinearLayout myuserrecord;
    @BindView(R2.id.myuserwallet)
    LinearLayout myuserwallet;
    @BindView(R2.id.myusercollect)
    LinearLayout myusercollect;
    @BindView(R2.id.myusersuggest)
    LinearLayout myusersuggest;
    @BindView(R2.id.myuservideo)
    LinearLayout myuservideo;
    @BindView(R2.id.myuserfriend)
    LinearLayout myuserfriend;
    @BindView(R2.id.myusertask)
    LinearLayout myusertask;
    @BindView(R2.id.myuserset)
    LinearLayout myuserset;
    @BindView(R2.id.myuserattention)
    LinearLayout myuserattention;
    private String uid=null;//userid
    private String sid=null;//senserid
    private UserSignPresenter userSignPresenter;
    private List<String> intt;
    //布局
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user;
    }

    //数据
    @Override
    protected void initView() {
        //p
        userSignPresenter = new UserSignPresenter(new getsigndata());
        //进行无线点击
        //返回点击
        myuserfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //签到点击
        myuserQiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    userSignPresenter.reqeust(uid,sid);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        //当前问诊点击
        myusernew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //历史问诊点击
        myuserhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //我的档案点击
        myuserrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    Intent intent = new Intent(MyUserActivity.this, MyUserRecordActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //我的收藏点击
        myusercollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    //跳转
                    Intent intent = new Intent(MyUserActivity.this, MyUserCollectActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //我的钱包点击
        myuserwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    //跳转
                    Intent intent = new Intent(MyUserActivity.this, MyUserWalletActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //查询被采纳的建议
        myusersuggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    //跳转
                    Intent intent = new Intent(MyUserActivity.this, MyUserSuggestActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //我的视频点击
        myuservideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        //设置跳转
        myuserset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt != null) {
                    //跳转
                    Intent intent = new Intent(MyUserActivity.this, MyUserSetActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MyUserActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //头像点击
        myuserheadportrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果是在登录状态
                if (intt != null) {

                }else{
                    //跳转登录
                    intentByRouter(Constant.ACTIVITY_LOGIN_LOGIN);
                }
            }
        });
    }
    @Override
    protected void destoryData() {

    }
    //回来的数据---------------------------------------------------------------
    class getsigndata implements DataCall{
        @Override
        public void success(Object data, Object... args) {
            myuserQiandao.setText("签到成功");
        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }
    //---------------------------------------------------------------获取焦点
    @Override
    protected void onResume() {
        super.onResume();
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        intt = loginDaoUtil.intt(MyUserActivity.this);
        if (intt != null) {
            //设置用户头像
            uid=intt.get(0);
            sid=intt.get(1);
            Glide.with(MyUserActivity.this).load(intt.get(2)).
                    apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myuserheadportrait);//头像
            myusername.setText(intt.get(3));//昵称
        }else{
            myusername.setText("请先登录!");//设置昵称
            Glide.with(this).load(R.drawable.register_icon_invitatiion_code_n).
                    apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myuserheadportrait);//设置头像
        }



    }
    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        userSignPresenter.unBind();//解绑
    }
}
