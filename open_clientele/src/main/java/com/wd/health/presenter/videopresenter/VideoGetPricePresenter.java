package com.wd.health.presenter.videopresenter;

import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/16 16:28
 * 描述：
 */
public class VideoGetPricePresenter extends WDPresenter<IAppRequest> {

    public VideoGetPricePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.videogetprice((String) args[0],(String) args[1]);
    }
}
