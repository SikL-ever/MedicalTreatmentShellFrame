package com.bw.message.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import cn.jmessage.support.qiniu.android.dns.IResolver;
import io.reactivex.Observable;

public class MyUserInquiryMessage extends WDPresenter<IAppRequest> {

    public MyUserInquiryMessage(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return iRequest.myuserinquiryMessage((String) args[0],(String) args[1],(int) args[2],(int) args[3]);
    }
}
