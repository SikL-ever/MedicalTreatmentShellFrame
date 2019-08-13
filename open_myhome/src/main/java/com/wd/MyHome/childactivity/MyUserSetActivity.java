package com.wd.MyHome.childactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import com.dingtao.common.util.DataCleanManager;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.MyHome.R;
import com.wd.MyHome.R2;
import com.wd.MyHome.activity.BzzxActivity;
import com.wd.MyHome.activity.GywmActivity;
import com.wd.MyHome.activity.MyUserActivity;
import com.wd.MyHome.activity.PmldActivity;
import com.wd.MyHome.activity.VersionsActivity;
import com.wd.MyHome.activity.WdxxActivity;
import com.wd.MyHome.activity.XgmmActivity;
import com.wd.MyHome.activity.YqhyActivity;
import com.wd.MyHome.childthreeactivity.SichangyongMyShenfenActivity;
import com.wd.MyHome.childthreeactivity.XieqiyinhangkaActivity;
import com.wd.MyHome.util.TopView;
import com.wd.health.util.MyDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;

public class MyUserSetActivity extends WDActivity {


    @BindView(R2.id.myuserset_top)
    TopView myusersetTop;
    @BindView(R2.id.myuserset_image)
    ImageView myusersetImage;
    @BindView(R2.id.myuserset_name)
    TextView myusersetName;
    @BindView(R2.id.logout)
    RelativeLayout logout;
    @BindView(R2.id.grxx)
    ImageView grxx;
    @BindView(R2.id.xgmm)
    ImageView xgmm;
    @BindView(R2.id.qchc)
    ImageView qchc;
    @BindView(R2.id.hc)
    TextView hc;
    @BindView(R2.id.pmld)
    ImageView pmld;
    @BindView(R2.id.bbjc)
    ImageView bbjc;
    @BindView(R2.id.bzzx)
    ImageView bzxx;
    @BindView(R2.id.gywm)
    ImageView gywm;
    @BindView(R2.id.yqhy)
    ImageView yqhy;
    private LoginBeanDao dao;//数据库
    private  boolean iosInterceptFlag;
    private PopupWindow window;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_user_set;
    }

    @Override
    protected void initView() {
        //顶部栏
        myusersetTop.setTitle("设置");
        dao = DaoMaster.newDevSession(this, LoginBeanDao.TABLENAME).getLoginBeanDao();//数据库@Override

        grxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyUserSetActivity.this,WdxxActivity.class));
            }
        });
        xgmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyUserSetActivity.this,XgmmActivity.class));
            }
        });
        String totalCacheSize = null;
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        hc.setText(totalCacheSize);

        qchc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除缓存弹窗
                View inflate = View.inflate(MyUserSetActivity.this, R.layout.activity_clcle_popupwindow, null);
                TextView quxiao , que;
                window = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                quxiao = inflate.findViewById(R.id.cicle_qu);
                que = inflate.findViewById(R.id.cicle_que);
                window.setTouchable(true);
                window.setFocusable(true);
                //window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lan_3087ea)));
                window.showAtLocation(inflate, Gravity.CENTER,0,0);
                window.showAsDropDown(inflate,100,100);

                //点击取消
                quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                    }
                });

                //点击确定
                que.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataCleanManager.clearAllCache(getApplicationContext());
                        //获取缓存的大小
                        try {
                            String totalCacheSize = DataCleanManager.getTotalCacheSize(getApplicationContext());
                            hc.setText(totalCacheSize);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        window.dismiss();
                    }
                });
            }
        });
        pmld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyUserSetActivity.this,PmldActivity.class));
            }
        });
        bbjc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyUserSetActivity.this,VersionsActivity.class));
            }
        });
        bzxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyUserSetActivity.this,BzzxActivity.class));
            }
        });
        gywm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyUserSetActivity.this,GywmActivity.class));
            }
        });
        yqhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyUserSetActivity.this,YqhyActivity.class));
            }
        });
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
                            //退出极光登录
                            JMessageClient.logout();
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
                } else {
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
