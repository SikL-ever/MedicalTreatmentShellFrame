package com.wd.health.presenter.wardmatepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import java.io.File;

import io.reactivex.Observable;

public class ImagePresenter extends WDPresenter<IAppRequest> {

    public ImagePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.image((String) args[0],(String) args[1],(int) args[2],(File) args[4]);
    }
}
