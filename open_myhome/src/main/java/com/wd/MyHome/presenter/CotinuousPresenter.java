package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;
import io.reactivex.Observable;

public class CotinuousPresenter extends WDPresenter<IAppRequest> {
    public CotinuousPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.continuous((String) args[0],(String) args[1]);
    }
}
