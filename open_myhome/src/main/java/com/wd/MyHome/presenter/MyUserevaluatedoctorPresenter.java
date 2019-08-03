package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class MyUserevaluatedoctorPresenter extends WDPresenter<IAppRequest> {

    public MyUserevaluatedoctorPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.evaluatedoctor((String)args[0],(String)args[1],(int)args[2],(int)args[3],(String)args[4],(int)args[5],(int)args[6]);
    }
}
