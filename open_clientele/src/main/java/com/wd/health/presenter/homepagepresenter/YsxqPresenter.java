package com.wd.health.presenter.homepagepresenter;

import com.dingtao.common.bean.homepage.YsxqBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import io.reactivex.Observable;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/29 13:41
 * @Description：描述信息
 */
public class YsxqPresenter extends WDPresenter<IAppRequest> {
    public YsxqPresenter(DataCall dataCall) {
        super(dataCall);
    }
    @Override
    protected Observable getModel(Object... args) {
        return iRequest.ysxqShow((String) args[0],(String) args[1],(int) args[2]);
    }
}
