package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/8/1 21:31
 * @Description：描述信息
 */
public class XgncPresenter extends WDPresenter<IAppRequest> {
    public XgncPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.xgncShow((String) args[0],(String) args[1],(String) args[2]);
    }
}
