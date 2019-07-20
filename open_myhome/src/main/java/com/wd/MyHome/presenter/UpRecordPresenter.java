package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * 佀常勇
 *
 * @Data:2019/7/19 20:36
 * 描述：
 */
public class UpRecordPresenter extends WDPresenter<IAppRequest> {

    public UpRecordPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.uprecord((String)args[0],(String)args[1],(int)args[2],(String)args[3],
                (String)args[4],(String)args[5],(String)args[6],(String)args[7],(String)args[8],(String)args[9]);
    }
}
