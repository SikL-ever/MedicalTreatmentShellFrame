package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;
import okhttp3.RequestBody;

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
        return iRequest.uprecord((String)args[0],(String)args[1],(RequestBody) args[2]);
    }
}
