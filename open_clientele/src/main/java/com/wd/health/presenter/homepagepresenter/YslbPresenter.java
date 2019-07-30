package com.wd.health.presenter.homepagepresenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/24 19:26
 * @Description：描述信息
 */
public class YslbPresenter extends WDPresenter<IAppRequest> {
    public YslbPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.yslbShow((String) args[0],(String) args[1],(int) args[2],(int) args[3],(int) args[4],(int) args[5],(int) args[6]);
    }
}
