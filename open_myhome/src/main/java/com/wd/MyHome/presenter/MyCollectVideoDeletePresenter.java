package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/20 17:00
 * 描述：
 */
public class MyCollectVideoDeletePresenter extends WDPresenter<IAppRequest> {
    public MyCollectVideoDeletePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.deletemyuservideo((String) args[0],(String) args[1],(int) args[2]);
    }
}
