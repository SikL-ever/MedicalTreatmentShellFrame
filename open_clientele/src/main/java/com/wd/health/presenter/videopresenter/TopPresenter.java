package com.wd.health.presenter.videopresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/12 9:47
 * 描述：
 */
public class TopPresenter extends WDPresenter<IAppRequest> {
    public TopPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.topdata();
    }
}
