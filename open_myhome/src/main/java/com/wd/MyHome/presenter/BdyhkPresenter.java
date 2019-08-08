package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/8/6 12:01
 * @Description：描述信息
 */
public class BdyhkPresenter extends WDPresenter<IAppRequest> {
    public BdyhkPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.bdyhShow((String) args[0],(String) args[1],(String) args[2],(String) args[3],(int) args[4]);
    }
}
