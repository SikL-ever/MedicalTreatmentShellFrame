package com.wd.login.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.util.Constant;
import com.wd.login.R;
import com.wd.login.R2;
import com.wd.login.adapter.GuidanceAdapter;
import com.wd.login.fragment.FiveFragment;
import com.wd.login.fragment.FourFragment;
import com.wd.login.fragment.OneFragment;
import com.wd.login.fragment.ThreeFragment;
import com.wd.login.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Constant.ACTIVITY_LOGIN_GUIDANCE)
public class GuidanceActivity extends WDActivity {

    @BindView(R2.id.welcomepage)
    ViewPager welcomepage;
    @BindView(R2.id.welcomeradio)
    RadioGroup welcomeradio;
    @BindView(R2.id.welcometextbt)
    TextView welcometextbt;

    List<Fragment> list = new ArrayList<>();
    private SharedPreferences two;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
    //存值
        //先常见sp存值
        two = getSharedPreferences("guidancesp", MODE_PRIVATE);
        boolean twoflay = two.getBoolean("twoflay", false);
        if (twoflay){
            //进入登录页面
            intentByRouter(Constant.ACTIVITY_LOGIN_LOGIN);
            finish();
        }
        //创建集合
        list.add(new OneFragment());
        list.add(new TwoFragment());
        list.add(new ThreeFragment());
        list.add(new FourFragment());
        list.add(new FiveFragment());
        //创建适配器
        GuidanceAdapter guidanceAdapter = new GuidanceAdapter(getSupportFragmentManager(), list);
        welcomepage.setAdapter(guidanceAdapter);
        //滑动事件
        welcomepage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                welcomeradio.check(welcomeradio.getChildAt(position).getId());
                // 判断是否是最后一页
                if (position == list.size() - 1) {
                    welcometextbt.setVisibility(View.VISIBLE);
                } else {
                    welcometextbt.setVisibility(View.GONE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //点击事件
        welcomeradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.welcomebt1) {
                    welcomepage.setCurrentItem(0, true);

                } else if (checkedId == R.id.welcomebt2) {
                    welcomepage.setCurrentItem(1, true);

                } else if (checkedId == R.id.welcomebt3) {
                    welcomepage.setCurrentItem(2, true);

                } else if (checkedId == R.id.welcomebt4) {
                    welcomepage.setCurrentItem(3, true);

                } else if (checkedId == R.id.welcomebt5) {
                    welcomepage.setCurrentItem(4, true);

                }
            }
        });
        //点击进入按钮
        welcometextbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //存值
                SharedPreferences.Editor edit = two.edit();
                edit.putBoolean("twoflay",true);
                edit.commit();
                //进入登录页面
                intentByRouter(Constant.ACTIVITY_LOGIN_LOGIN);
                finish();
            }
        });
    }

    @Override
    protected void destoryData() {

    }
}
