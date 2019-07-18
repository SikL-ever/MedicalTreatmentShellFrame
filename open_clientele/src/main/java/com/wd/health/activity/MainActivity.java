package com.wd.health.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.LoginDaoUtil;
import com.wd.health.R;
import com.wd.health.R2;
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
    private FragmentManager manager;
    private HomePagerFragement homePagerFragement;
    private WardmateFragment wardmateFragment;
    private VideoFragment videoFragment;
    private FragmentTransaction transaction;
    private int page = 3;
    private boolean videobo=false;
    //创建hind
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            page--;
            handler.sendEmptyMessageDelayed(0, 1000);
            if (bt3.isChecked()==false){
                mainshowhide.setVisibility(View.GONE);
                mainradio.setVisibility(View.VISIBLE);
                page=3;
                handler.removeMessages(0);
            }else{
                if (page == 0) {
                    mainradio.setVisibility(View.GONE);//导航消失
                    mainshowhide.setVisibility(View.VISIBLE);//小导航栏出现
                    page=3;
                    handler.removeMessages(0);
                }
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    videobo=false;
                } else if (checkedId == R.id.bt2) {
                    for (int i = 0; i < 2; i++) {
                        if (bt2.isChecked()){
                            bt2.setChecked(false);
                            bt2.setVisibility(View.VISIBLE);
                            bt4.setVisibility(View.GONE);
                        }else{
                            transaction.show(wardmateFragment).hide(homePagerFragement).hide(videoFragment);
                            bt4.setVisibility(View.VISIBLE);
                            bt2.setVisibility(View.GONE);
                            bt4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(MainActivity.this, "正在开发", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                    videobo=false;
                } else if (checkedId == R.id.bt3) {
                    transaction.show(videoFragment).hide(homePagerFragement).hide(wardmateFragment);
                    mainradio.setVisibility(View.GONE);//导航消失
                    mainshowhide.setVisibility(View.VISIBLE);//小导航栏出现
                    bt2.setVisibility(View.VISIBLE);
                    bt4.setVisibility(View.GONE);
                    videobo=true;
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
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        });
        //点击发表病友圈
        //进行用户判断//判断用户时候登陆这
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(MainActivity.this);
        if (intt == null) {
            Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }
    //写一个方法视频页面用的
    public boolean vdeodata(){
        if (videobo) {
            return true;
        }
        return false;
    }
    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }
}
