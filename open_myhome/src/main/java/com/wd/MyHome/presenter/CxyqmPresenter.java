package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/8/10 9:31
 * @Description：描述信息
 */
public class CxyqmPresenter extends WDPresenter<IAppRequest> {
    public CxyqmPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.cxyqmShow((String) args[0],(String) args[1]);
    }
}
