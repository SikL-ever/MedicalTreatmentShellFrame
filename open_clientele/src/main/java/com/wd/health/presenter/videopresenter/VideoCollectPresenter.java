package com.wd.health.presenter.videopresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/16 21:21
 * 描述：
 */
public class VideoCollectPresenter extends WDPresenter<IAppRequest> {
    public VideoCollectPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.videocollect((String) args[0],(String) args[1],(int) args[2]);
    }
}
