package com.wd.health.presenter.homepagepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/22 15:36
 * @Description：描述信息
 */
public class ZxscPresenter extends WDPresenter<IAppRequest> {
    public ZxscPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.shoucangshow((String) args[0],(String) args[1],(int) args[2]);
    }
}
