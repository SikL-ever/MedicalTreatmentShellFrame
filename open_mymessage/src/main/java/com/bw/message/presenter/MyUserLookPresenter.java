package com.bw.message.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class MyUserLookPresenter extends WDPresenter<IAppRequest> {

    public MyUserLookPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.lookMessage((String)args[0],(String)args[1]);
    }
}
