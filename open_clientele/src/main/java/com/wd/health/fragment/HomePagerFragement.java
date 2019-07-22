package com.wd.health.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.bean.homepage.Banner;
import com.dingtao.common.bean.homepage.DuotiaomuBean;
import com.dingtao.common.bean.homepage.WzzxBean;
import com.dingtao.common.bean.homepage.ZxbkBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.recker.flybanner.FlyBanner;
import com.wd.health.CustomImageView.ScrollLevitateTabView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.activity.DuoXiangActivity;
import com.wd.health.activity.SousuoAcitivity;
import com.wd.health.activity.ZsbdActivity;
import com.wd.health.adapter.homepageadapter.DuotiaomuAdapter;
import com.wd.health.adapter.homepageadapter.WzzxAdapter;
import com.wd.health.presenter.homepagepresenter.BannerPresenter;
import com.wd.health.presenter.homepagepresenter.DuotiaomuPresenter;
import com.wd.health.presenter.homepagepresenter.WzzxPresenter;
import com.wd.health.presenter.homepagepresenter.ZxbkPresenter;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    TextView edit;
    @BindView(R2.id.ckgd)
    TextView ckgd;
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
    RecyclerView recycler;
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


    //private String[] longTitles = new String[]{"推荐", "热点", "北京", "视频", "社会", "图片", "娱乐", "科技", "汽车"};
    private BannerPresenter bannerPresenter;
    private WzzxAdapter wzzxAdapter;
    private WzzxPresenter wzzxPresenter;
    private ZxbkPresenter zxbkpresenter;
    private DuotiaomuPresenter duotiaomuPresenter;
    private DuotiaomuAdapter duotiaomuAdapter;
    private String title_name = "健康养生";

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
        //ScrollView和recyclerview的滑动冲突解决
        recycler.setNestedScrollingEnabled(false);
        frage.setNestedScrollingEnabled(false);

        //p
        bannerPresenter = new BannerPresenter(new getbanner());
        bannerPresenter.reqeust();
        recycler.setLayoutManager(new GridLayoutManager(getActivity(),4));
        wzzxPresenter = new WzzxPresenter(new Wzzx());
        wzzxPresenter.reqeust();
        zxbkpresenter = new ZxbkPresenter(new Zxbk());
        zxbkpresenter.reqeust();
        duotiaomuPresenter = new DuotiaomuPresenter(new Dtm());
        duotiaomuPresenter.reqeust(1,1,10);
        //   Wenzhenzixun();
        sltv.setOnScrollLintener(new ScrollLevitateTabView.OnScrollLintener() {
            @Override
            public void onScroll(int scrollY) {
                int top = Math.max(scrollY, frage.getTop());
                tabLayout3.layout(0, top, tabLayout3.getWidth(), top + tabLayout3.getHeight());
            }
        });


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
        cjbz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ZsbdActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
            }
        });
        cyyp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ZsbdActivity.class);
                intent.putExtra("type",0);
                startActivity(intent);
            }
        });
        ckgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DuoXiangActivity.class);
                intent.putExtra("asdf",title_name);
                getActivity().startActivity(intent);
            }
        });
        //frage.setLayoutManager(new LinearLayoutManager(this,));
        frage.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        duotiaomuAdapter = new DuotiaomuAdapter(getContext());
        frage.setAdapter(duotiaomuAdapter);
        tabLayout3.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                duotiaomuPresenter = new DuotiaomuPresenter(new Dtm());
                duotiaomuPresenter.reqeust(tab.getPosition()+1,1,10);
                title_name=tab.getText()+"";
                //recycler.setFocusableInTouchMode(false);
                Log.i("aaaaaaaaa",tab.getText()+"+++++++++"+tab.getPosition()+"++++++++"+tab.getIcon()+"++++++++++++++++++"+title_name);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void Shujvzhanshi(int i) {

    }

    /*//问诊咨询
    private void Wenzhenzixun() {


    }*/
    private class Wzzx implements DataCall<List<WzzxBean>> {
        @Override
        public void success(List<WzzxBean> data, Object... args) {
            Log.e("aaaaaaaaaaa",data.get(0).getDepartmentName());
            wzzxAdapter = new WzzxAdapter(getContext(),data);
            recycler.setAdapter(wzzxAdapter);
            wzzxAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Log.e("aaaaaaaaaaa",data.getDisplayMessage());
        }
    }
    private class getbanner implements DataCall<List<Banner>>{
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
    private ArrayList<String> stringArrayList = new ArrayList<String>();
    private class Zxbk implements DataCall<List<ZxbkBean>> {
        @Override
        public void success(List<ZxbkBean> data, Object... args) {
            Log.e( "sssssssssssss",data.get(0).getName());
            for (int i = 0; i < data.size(); i++) {
                String name = data.get(i).getName();
                stringArrayList.add(name); //add to arraylist
            }
            Log.e( "sssssssssssss",stringArrayList.get(0)+stringArrayList.get(1)+stringArrayList.get(2));
            setScroll();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    private void setScroll() {

        for (int i = 0; i < stringArrayList.size(); i++) {
            tabLayout3.addTab(tabLayout3.newTab());
        }

        for (int i = 0; i < stringArrayList.size(); i++) {
            tabLayout3.getTabAt(i).setText(stringArrayList.get(i));
        }
    }
    private class Dtm implements DataCall<List<DuotiaomuBean>> {
        @Override
        public void success(List<DuotiaomuBean> data, Object... args) {
            duotiaomuAdapter.setList(data);
            duotiaomuAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
