package com.wd.health.presenter.wardmatepresenter;

import android.icu.text.MessagePattern;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import java.io.File;

import io.reactivex.Observable;

public class ImagePresenter extends WDPresenter<IAppRequest> {

    public ImagePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
        return null;
    }
}
