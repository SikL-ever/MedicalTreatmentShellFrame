package com.wd.login.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/11 15:19
 * 描述：
 */
public class AnewPassPresenter extends WDPresenter<IAppRequest> {

    public AnewPassPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.anewpass((String) args[0],(String) args[1],(String) args[2]);
    }
}
