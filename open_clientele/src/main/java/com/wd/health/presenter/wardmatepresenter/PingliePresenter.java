package com.wd.health.presenter.wardmatepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class PingliePresenter extends WDPresenter<IAppRequest> {
    public PingliePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.ping((String) args[0], (String) args[1],(int) args[2],(int) args[3],(int) args[4]);
    }
}
