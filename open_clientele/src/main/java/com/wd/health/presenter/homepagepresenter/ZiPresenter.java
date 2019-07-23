package com.wd.health.presenter.homepagepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/17 14:16
 * @Description：描述信息
 */
public class ZiPresenter extends WDPresenter<IAppRequest> {
    public ZiPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.zishow((int)args [0]);
    }
}
