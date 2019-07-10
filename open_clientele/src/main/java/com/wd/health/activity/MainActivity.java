package com.wd.health.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.dingtao.common.core.WDActivity;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.fragment.HomePagerFragement;
import com.wd.health.fragment.VideoFragment;
import com.wd.health.fragment.WardmateFragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

public class MainActivity extends WDActivity {
    @BindView(R2.id.mainfragment)
    FrameLayout mainfragment;
    @BindView(R2.id.mainradio)
    RadioGroup mainradio;
    private FragmentManager manager;
    private HomePagerFragement homePagerFragement;
    private WardmateFragment wardmateFragment;
    private VideoFragment videoFragment;
    private FragmentTransaction transaction;

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
        }else{  // 正常时
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

                } else if (checkedId == R.id.bt2) {
                    transaction.show(wardmateFragment).hide(homePagerFragement).hide(videoFragment);

                } else if (checkedId == R.id.bt3) {
                    transaction.show(videoFragment).hide(homePagerFragement).hide(wardmateFragment);

                }
                transaction.commit();
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void destoryData() {

    }
}
