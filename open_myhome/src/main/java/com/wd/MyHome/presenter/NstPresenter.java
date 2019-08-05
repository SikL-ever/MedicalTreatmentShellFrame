package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/8/3 12:19
 * @Description：描述信息
 */
public class NstPresenter extends WDPresenter<IAppRequest> {
    public NstPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.nstShow((String) args[0],(String) args[1],(int) args[2],(int) args[3],(int) args[4]);
    }
}
