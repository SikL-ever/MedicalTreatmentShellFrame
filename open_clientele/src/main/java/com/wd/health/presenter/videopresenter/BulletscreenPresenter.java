package com.wd.health.presenter.videopresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/15 11:36
 * 描述：
 */
public class BulletscreenPresenter extends WDPresenter<IAppRequest>
{
    public BulletscreenPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.videodan((int) args[0]);
    }
}
