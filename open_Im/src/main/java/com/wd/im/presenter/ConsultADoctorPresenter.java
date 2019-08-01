package com.wd.im.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

public class ConsultADoctorPresenter extends WDPresenter<IAppRequest> {

    public ConsultADoctorPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.consultAdoctor((String) args[0],(String)args[1],(int)args[2]);
    }
}
