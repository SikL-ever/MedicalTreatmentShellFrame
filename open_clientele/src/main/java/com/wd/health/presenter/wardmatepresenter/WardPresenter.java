package com.wd.health.presenter.wardmatepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/12 9:38
 * 描述：
 */
public class WardPresenter extends WDPresenter<IAppRequest> {
    public WardPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.wardLie((int) args[0],(int) args[1],(int) args[2]);
    }
}
