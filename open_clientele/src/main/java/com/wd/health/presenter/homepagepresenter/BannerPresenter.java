package com.wd.health.presenter.homepagepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/10 16:04
 * 描述：
 */
public class BannerPresenter extends WDPresenter<IAppRequest> {
    public BannerPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.bannershow();
    }
}
