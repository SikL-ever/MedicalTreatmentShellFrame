package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class PingliesPresenter extends WDPresenter<IAppRequest> {

    public PingliesPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.ping((String) args[0], (String) args[1],(int) args[2],(int) args[3],(int) args[4]);
    }
}
