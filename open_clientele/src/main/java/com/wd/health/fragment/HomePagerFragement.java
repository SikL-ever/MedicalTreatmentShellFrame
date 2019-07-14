package com.wd.health.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dingtao.common.bean.homepage.Banner;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.recker.flybanner.FlyBanner;
import com.wd.health.CustomImageView.ScrollLevitateTabView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.activity.SousuoAcitivity;
import com.wd.health.presenter.homepagepresenter.BannerPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 佀常勇
 *
 * @Data:2019/7/10 15:17
 * 描述：
 */
public class HomePagerFragement extends WDFragment {
    @BindView(R2.id.recyclerView)
    RecyclerView frage;
    @BindView(R2.id.tablayout3)
    TabLayout tabLayout3;
    @BindView(R2.id.sltv)
    ScrollLevitateTabView sltv;
    @BindView(R2.id.flybanner)
    FlyBanner flybanner;
    @BindView(R2.id.images)
    ImageView images;
    @BindView(R2.id.edit)
    EditText edit;
    @BindView(R2.id.xiaoxi)
    CheckBox xiaoxi;
    @BindView(R2.id.zsbd)
    LinearLayout zsbd;
    @BindView(R2.id.cjbz_image)
    ImageView cjbzImage;
    @BindView(R2.id.cjbz)
    LinearLayout cjbz;
    @BindView(R2.id.cyyp_image)
    ImageView cyypImage;
    @BindView(R2.id.cyyp)
    LinearLayout cyyp;
    @BindView(R2.id.a)
    RelativeLayout a;
    @BindView(R2.id.wzzx)
    LinearLayout wzzx;
    @BindView(R2.id.recycler)
    XRecyclerView recycler;
    @BindView(R2.id.c)
    RelativeLayout c;
    @BindView(R2.id.jkpc)
    LinearLayout jkpc;
    @BindView(R2.id.jkcp)
    ImageView jkcp;
    @BindView(R2.id.d)
    LinearLayout d;
    @BindView(R2.id.jkzx)
    LinearLayout jkzx;
    private String[] longTitles = new String[]{"推荐", "热点", "北京", "视频", "社会", "图片", "娱乐", "科技", "汽车"};
    private BannerPresenter bannerPresenter;
    /*private TabFragment1 tabFragment1;
    private FrameLayout frage;
    private TabLayout tabLayout, tabLayout2, tabLayout3;
    private ScrollLevitateTabView sltv;
    private TextView flow_tab2;*/

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.homepage;
    }

    @Override
    protected void initView() {
        //p
        bannerPresenter = new BannerPresenter(new getbanner());
        bannerPresenter.reqeust();


        sltv.setOnScrollLintener(new ScrollLevitateTabView.OnScrollLintener() {
            @Override
            public void onScroll(int scrollY) {
                int top = Math.max(scrollY, frage.getTop());
                tabLayout3.layout(0, top, tabLayout3.getWidth(), top + tabLayout3.getHeight());
            }
        });
        setScroll();

        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SousuoAcitivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);

            }
        });


    }
    class getbanner implements DataCall<List<Banner>>{
        @Override
        public void success(List<Banner> data, Object... args) {
            List<String> list = new ArrayList<String>();

            for (int i=0; i<data.size();i++){
                list.add(data.get(i).getImageUrl());
            }
            flybanner.setImagesUrl(list);

//        点击事件
            flybanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getActivity(), "点击了第"+position+"照片", Toast.LENGTH_SHORT).show();
                }
            });


        }
        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    private void setScroll() {

        for (int i = 0; i < longTitles.length; i++) {
            tabLayout3.addTab(tabLayout3.newTab());
        }

        for (int i = 0; i < longTitles.length; i++) {
            tabLayout3.getTabAt(i).setText(longTitles[i]);
        }
    }
}
