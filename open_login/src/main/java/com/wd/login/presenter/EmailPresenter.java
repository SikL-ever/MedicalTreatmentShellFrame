package com.wd.login.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/10 21:23
 * 描述：
 */
public class EmailPresenter extends WDPresenter<IAppRequest> {
    public EmailPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getemail((String) args[0]);
    }
}
