package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/8/2 11:37
 * @Description：描述信息
 */
public class XgxbPresenter extends WDPresenter<IAppRequest> {
    public XgxbPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.sexShow((String) args[0],(String) args[1],(int) args[2]);
    }
}
