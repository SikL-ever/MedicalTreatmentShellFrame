package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class MyUserGiveGiftPresenter extends WDPresenter<IAppRequest> {

    public MyUserGiveGiftPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.giveGift((String)args[0],(String)args[1],(int)args[2],(int)args[3]);
    }
}
