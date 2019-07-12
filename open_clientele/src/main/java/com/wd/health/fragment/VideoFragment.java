package com.wd.health.fragment;

import android.util.Log;

import com.dingtao.common.bean.video.TopBean;
import com.dingtao.common.bean.video.VideoBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.LoginDaoUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.videoadapter.VerticalViewPagerAdapter;
import com.wd.health.adapter.videoadapter.VideoMovieFragment;
import com.wd.health.presenter.videopresenter.TopPresenter;
import com.wd.health.presenter.videopresenter.VideoPresenter;
import com.wd.health.util.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * 佀常勇
 *
 * @Data:2019/7/10 15:19
 * 描述：
 */

public class VideoFragment extends WDFragment {


    @BindView(R2.id.videomovie)
    VerticalViewPager videomovie;
    @BindView(R2.id.srl_page)
    SmartRefreshLayout srlPage;
    private TopPresenter topPresenter;
    private VideoPresenter videoPresenter;
    private List<String> urlList=new ArrayList<>();
    private VerticalViewPagerAdapter pagerAdapter;

    @Override
    public String getPageName() {
        return null;
    }

    //视图
    @Override
    protected int getLayoutId() {
        return R.layout.video;
    }

    //数据
    @Override
    protected void initView() {

        //创建p层
        topPresenter = new TopPresenter(new gettopdata());//请求顶部栏的数据
        videoPresenter = new VideoPresenter(new getvideodata());


    }
    //顶部栏的数据
    class gettopdata implements DataCall<List<TopBean>> {
        @Override
        public void success(List<TopBean> data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    //请求下来的视频
    class getvideodata implements DataCall<List<VideoBean>> {
        @Override
        public void success(List<VideoBean> data, Object... args) {
            for (int i = 0; i < data.size(); i++) {
                VideoBean videoBean = data.get(i);
                urlList.add(videoBean.originalUrl);
            }
            inittView();
            addListener();
        }

        @Override
        public void fail(ApiException data, Object... args) {
        }
    }

    //获取焦点的时候去请求数据
    @Override
    public void onResume() {
        super.onResume();
        //进行用户判断//判断用户时候登陆这
        LoginDaoUtil loginDaoUtil = new LoginDaoUtil();
        List<String> intt = loginDaoUtil.intt(getContext());
        if (intt == null) {

        } else {
            videoPresenter.reqeust(intt.get(0), intt.get(1), 1, 1, 10);
        }
        topPresenter.reqeust();
    }

    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        topPresenter.unBind();//解绑
        videoPresenter.unBind();
    }

    private void addListener() {
        srlPage.setEnableAutoLoadMore(false);
        srlPage.setEnableLoadMore(false);
        srlPage.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                srlPage.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        urlList.addAll(urlList);
                        pagerAdapter.setUrlList(urlList);
                        pagerAdapter.notifyDataSetChanged();

                        srlPage.finishLoadMore();
                    }
                }, 2000);
            }
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            }
        });
    }

    private void inittView() {
        pagerAdapter = new VerticalViewPagerAdapter(getChildFragmentManager());
        videomovie.setVertical(true);
        videomovie.setOffscreenPageLimit(10);
        pagerAdapter.setUrlList(urlList);
        videomovie.setAdapter(pagerAdapter);
        videomovie.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (position == urlList.size() - 1) {
                    srlPage.setEnableAutoLoadMore(true);
                    srlPage.setEnableLoadMore(true);
                } else {
                    srlPage.setEnableAutoLoadMore(false);
                    srlPage.setEnableLoadMore(false);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    //失去焦点
    @Override
    public void onPause() {
        super.onPause();
    }

}
