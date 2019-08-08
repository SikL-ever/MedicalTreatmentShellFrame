package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;
import io.reactivex.Observable;

public class WhetherPresenter extends WDPresenter<IAppRequest> {
    public WhetherPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.whether((String) args[0],(String) args[1]);
    }
}
