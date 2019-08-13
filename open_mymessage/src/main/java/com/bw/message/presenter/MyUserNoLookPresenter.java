package com.bw.message.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class MyUserNoLookPresenter extends WDPresenter<IAppRequest> {

    public MyUserNoLookPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.nolook((String) args[0],(String)args[1]);
    }
}
