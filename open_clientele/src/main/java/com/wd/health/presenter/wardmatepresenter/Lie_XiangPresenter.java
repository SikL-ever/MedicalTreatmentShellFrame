package com.wd.health.presenter.wardmatepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class Lie_XiangPresenter extends WDPresenter<IAppRequest> {
    public Lie_XiangPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.ListXiang((String) args[0],(String) args[1],(int)args[2]);
    }
}
