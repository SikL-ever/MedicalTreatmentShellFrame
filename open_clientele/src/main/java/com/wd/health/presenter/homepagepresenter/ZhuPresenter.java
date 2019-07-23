package com.wd.health.presenter.homepagepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/17 15:03
 * @Description：描述信息
 */
public class ZhuPresenter extends WDPresenter<IAppRequest> {
    public ZhuPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.zhushow((int) args[0]);
    }
}
