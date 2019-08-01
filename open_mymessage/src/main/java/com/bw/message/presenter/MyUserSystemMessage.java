package com.bw.message.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class MyUserSystemMessage extends WDPresenter<IAppRequest> {

    public MyUserSystemMessage(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.myusersystemMessage((String) args[0],(String) args[1],(int) args[2],(int) args[3]);
    }
}
