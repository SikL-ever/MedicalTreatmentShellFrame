package com.wd.health.presenter.homepagepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/15 13:51
 * @Description：描述信息
 */
public class SousuoPresenter extends WDPresenter<IAppRequest> {
    public SousuoPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.sousuoshow((String) args[0]);
    }
}
