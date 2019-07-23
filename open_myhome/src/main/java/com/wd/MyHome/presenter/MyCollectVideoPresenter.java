package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/20 10:52
 * 描述：
 */
public class MyCollectVideoPresenter extends WDPresenter<IAppRequest> {

    public MyCollectVideoPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.mycollectvideo((String)args[0],(String) args[1],(int)args[2],(int) args[3]);
    }
}
