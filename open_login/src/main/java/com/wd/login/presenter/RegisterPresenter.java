package com.wd.login.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/10 21:24
 * 描述：
 */
public class RegisterPresenter extends WDPresenter<IAppRequest> {

    public RegisterPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.getregister((String) args[0],(String) args[1],(String) args[2],(String) args[3],(String) args[4]);
    }
}
