package com.wd.health.presenter.videopresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/12 10:11
 * 描述：
 */
public class VideoPresenter extends WDPresenter<IAppRequest> {

    public VideoPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.videodata((String) args[0],(String) args[1],(int) args[2],(int) args[3],(int) args[4]);
    }
}
