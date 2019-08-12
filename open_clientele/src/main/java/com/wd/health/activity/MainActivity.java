package com.wd.health.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.activity.wardActivity.PublishActivity;
import com.wd.health.fragment.HomePagerFragement;
import com.wd.health.fragment.VideoFragment;
import com.wd.health.fragment.WardmateFragment;

import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

@Route(path = Constant.ACTIVITY_LOGIN_MAIN)
public class MainActivity extends WDActivity {
    @BindView(R2.id.mainfragment)
    FrameLayout mainfragment;
    @BindView(R2.id.mainradio)
    RadioGroup mainradio;
    @BindView(R2.id.mainbt)
    ImageView mainbt;
    @BindView(R2.id.mainshowhide)
    RelativeLayout mainshowhide;
    @BindView(R2.id.bt2)
    RadioButton bt2;
    @BindView(R2.id.bt3)
    RadioButton bt3;
    @BindView(R2.id.bt4)
    RadioButton bt4;
    @BindView(R2.id.topimage)
    ImageView topimage;
    @BindView(R2.id.topimagetwo)
    ImageView topimagetwo;
    @BindView(R2.id.toplayout)
    RelativeLayout toplayout;
    private FragmentManager manager;
    private HomePagerFragement homePagerFragement;
    private WardmateFragment wardmateFragment;
    private VideoFragment videoFragment;
    private FragmentTransaction transaction;
    private int page = 3;
    private boolean videobo = false;
    //创建hind
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            page--;
            handler.sendEmptyMessageDelayed(0, 1000);
            if (bt3.isChecked() == false) {
                mainshowhide.setVisibility(View.GONE);
                mainradio.setVisibility(View.VISIBLE);
                //bt2.setVisibility(View.VISIBLE);
                toplayout.setVisibility(View.GONE);//顶部栏
                page = 3;
                handler.removeMessages(0);
            } else {
                if (page == 0) {
                    mainradio.setVisibility(View.GONE);//导航消失
                    //bt2.setVisibility(View.GONE);
                    mainshowhide.setVisibility(View.VISIBLE);//小导航栏出现
                    toplayout.setVisibility(View.GONE);//顶部栏
                    page = 3;
                    handler.removeMessages(0);
                }
            }
        }
    };
    private List<String> intt;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intt == null) {
                    //跳转到登录页面
                    intentByRouter(Constant.ACTIVITY_LOGIN_LOGIN);
                } else {
                    //跳转到我的页面
                    intentByRouter(Constant.ACTIVITY_LOGIN_MYUSERACTIVITY);
                }
            }
        });
        //的第二张图片
        topimagetwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进行判断然后跳转消息页面
                if (intt == null) {
                    Toast.makeText(MainActivity.this, "您不在登录状态", Toast.LENGTH_SHORT).show();
                } else {
                    intentByRouter(Constant.ACTIVITY_MYUSERMESSAGE);
                }
            }
        });
        //创建管理者添加Fragment
        manager = getSupportFragmentManager();
        if (savedInstanceState != null) {  // “内存重启”时调用
            // 解决重叠问题
            manager.beginTransaction()
                    .show(homePagerFragement)
                    .hide(wardmateFragment)
                    .hide(videoFragment)
                    .commit();
        } else {  // 正常时
            homePagerFragement = new HomePagerFragement();
            wardmateFragment = new WardmateFragment();
            videoFragment = new VideoFragment();
            manager.beginTransaction()
                    .add(R.id.mainfragment, homePagerFragement)
                    .add(R.id.mainfragment, wardmateFragment)
                    .add(R.id.mainfragment, videoFragment)
                    .show(homePagerFragement)
                    .hide(wardmateFragment)
                    .hide(videoFragment)
                    .commit();
        }
        mainradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                transaction = manager.beginTransaction();
                if (checkedId == R.id.bt1) {
                    transaction.show(homePagerFragement).hide(wardmateFragment).hide(videoFragment);
                    bt2.setVisibility(View.VISIBLE);
                    bt4.setVisibility(View.GONE);
                    videobo = false;
                } else if (checkedId == R.id.bt2) {
                    for (int i = 0; i < 2; i++) {
                        if (bt2.isChecked()) {
                            bt2.setChecked(false);
                            bt2.setVisibility(View.VISIBLE);
                            bt4.setVisibility(View.GONE);
                        } else {
                            transaction.show(wardmateFragment).hide(homePagerFragement).hide(videoFragment);
                            bt4.setVisibility(View.VISIBLE);
                            bt2.setVisibility(View.GONE);
                            bt4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
                                    List<String> intt = loginDaoUtil.intt(MainActivity.this);
                                    if (intt == null) {
                                        intentByRouter(Constant.ACTIVITY_LOGIN_LOGIN);
                                    } else {
                                        Intent intent = new Intent(MainActivity.this, PublishActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }
                    videobo = false;
                } else if (checkedId == R.id.bt3) {
                    transaction.show(videoFragment).hide(homePagerFragement).hide(wardmateFragment);
                    mainradio.setVisibility(View.GONE);//导航消失
                    //bt2.setVisibility(View.GONE);
                    mainshowhide.setVisibility(View.VISIBLE);//小导航栏出现
                    bt2.setVisibility(View.VISIBLE);
                    bt4.setVisibility(View.GONE);
                    videobo = true;
                }
                transaction.commit();
            }
        });

        //点击切换底部栏
        mainbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainradio.setVisibility(View.VISIBLE);//导航消失
                mainshowhide.setVisibility(View.GONE);//小导航栏出现
                //bt2.setVisibility(View.VISIBLE);
                toplayout.setVisibility(View.VISIBLE);
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //进行用户判断//判断用户时候登陆这
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        intt = loginDaoUtil.intt(MainActivity.this);
        if (intt == null) {
            Glide.with(MainActivity.this).load(R.drawable.register_icon_invitatiion_code_n).
                    apply(RequestOptions.bitmapTransform(new CircleCrop())).into(topimage);
        } else {
            Glide.with(MainActivity.this).load(intt.get(2)).
                    apply(RequestOptions.bitmapTransform(new CircleCrop())).into(topimage);
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }

    //-----------------------------------------------两次点击退出
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
