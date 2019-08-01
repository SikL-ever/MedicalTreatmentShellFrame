package com.wd.im.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class SendMessagePresenter extends WDPresenter<IAppRequest> {

    public SendMessagePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.sendmessage((String)args[0],(String)args[1],(int)args[2],(String)args[3],(String)args[4],(int)args[5],(int)args[6]);
    }
}
