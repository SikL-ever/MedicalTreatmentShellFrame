package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class MyUserLookNewInquiry extends WDPresenter<IAppRequest> {
    public MyUserLookNewInquiry(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.looknewinquiry((String)args[0],(String)args[1]);
    }
}
