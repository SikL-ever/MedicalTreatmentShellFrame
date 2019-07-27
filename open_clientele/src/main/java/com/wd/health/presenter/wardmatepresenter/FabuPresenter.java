package com.wd.health.presenter.wardmatepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public class FabuPresenter extends WDPresenter<IAppRequest> {
    public FabuPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.fabiao((String) args[0],(String) args[1],(RequestBody) args[2]);
    }
}
