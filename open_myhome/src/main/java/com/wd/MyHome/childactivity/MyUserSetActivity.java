package com.wd.MyHome.childactivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.dao.DaoMaster;
import com.dingtao.common.dao.LoginBeanDao;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.util.TopView;
import com.wd.health.util.MyDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyUserSetActivity extends WDActivity {


    @BindView(R2.id.myuserset_top)
    TopView myusersetTop;
    @BindView(R2.id.myuserset_image)
    ImageView myusersetImage;
    @BindView(R2.id.myuserset_name)
    TextView myusersetName;
    @BindView(R2.id.logout)
    RelativeLayout logout;
    private LoginBeanDao dao;//数据库
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_set;
    }

    @Override
    protected void initView() {
        //顶部栏
        myusersetTop.setTitle("设置");
        dao=DaoMaster.newDevSession(this,LoginBeanDao.TABLENAME).getLoginBeanDao();//数据库
        //退出登录点击
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new LoginDaoUtil().intt(MyUserSetActivity.this)==null) {
                    Toast.makeText(MyUserSetActivity.this, "已经是退出登录状态", Toast.LENGTH_SHORT).show();
                }else{
                    View view=View.inflate(MyUserSetActivity.this, com.wd.health.R.layout.videodialong_item,null);
                    final MyDialog dialog = new MyDialog(MyUserSetActivity.this, 200, 100, view, com.wd.health.R.style.dialog);
                    dialog.show();
                    final TextView cancel =
                            (TextView) view.findViewById(com.wd.health.R.id.cancel);
                    final TextView confirm =
                            (TextView)view.findViewById(com.wd.health.R.id.confirm);
                    final TextView text =
                            (TextView)view.findViewById(com.wd.health.R.id.textView10);
                    text.setText("是否确认退出登录");
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //退出登录 循环数据库吧清空数据库
                            dao.deleteAll();
                            myusersetName.setText("请先登录!");//设置昵称
                            Glide.with(MyUserSetActivity.this).load(R.drawable.register_icon_invitatiion_code_n).
                                    apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myusersetImage);//设置头像
                            Toast.makeText(MyUserSetActivity.this, "退出登录成功", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            //跳转登录
                            intentByRouter(Constant.ACTIVITY_LOGIN_LOGIN);
                        }
                    });
                }
            }
        });
        //退出状态下的头像点击
        myusersetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new LoginDaoUtil().intt(MyUserSetActivity.this) == null) {
                    //跳转登录
                    intentByRouter(Constant.ACTIVITY_LOGIN_LOGIN);
                }else{
                    //无操作
                }
            }
        });
    }

    @Override
    protected void destoryData() {

    }
    //

    @Override
    protected void onResume() {
        super.onResume();
        //得到数据库的值
        List<String> intt = new LoginDaoUtil().intt(this);
        //1uid,2sid,3头像，4昵称
        if (intt == null) {
            myusersetName.setText("请先登录!");//设置昵称
            Glide.with(this).load(R.drawable.register_icon_invitatiion_code_n).
                    apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myusersetImage);//设置头像
        }else{
            Glide.with(this).load(intt.get(2)).
                    apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myusersetImage);//设置头像
            myusersetName.setText(intt.get(3));//设置昵称
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
