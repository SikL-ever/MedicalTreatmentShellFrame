package com.wd.health.fragment;

import android.util.Log;

import com.dingtao.common.bean.homepage.Banner;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.wd.health.R;
import com.wd.health.presenter.homepagepresenter.BannerPresenter;

import java.util.List;

/**
 * 佀常勇
 *
 * @Data:2019/7/10 15:18
 * 描述：
 */
public class WardmateFragment extends WDFragment {
    @Override
    public String getPageName() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.wardmate;
    }

    @Override
    protected void initView() {
        BannerPresenter bannerPresenter = new BannerPresenter(new getdata());
        bannerPresenter.reqeust();
    }
    class getdata implements DataCall<List<Banner>>{
        @Override
        public void success(List<Banner> data, Object... args) {
            Log.i("aaa", "success: "+data);
        }
        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
