package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;
import io.reactivex.Observable;

public class DeleterPresenters extends WDPresenter<IAppRequest> {
    public DeleterPresenters(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.cancel((String) args[0],(String) args[1],(int) args[2]);
    }
}
