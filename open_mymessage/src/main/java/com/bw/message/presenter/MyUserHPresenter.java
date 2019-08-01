package com.bw.message.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class MyUserHPresenter extends WDPresenter<IAppRequest> {

    public MyUserHPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.myuserHMessage((String) args[0],(String) args[1],(int) args[2],(int) args[3]);
    }
}
