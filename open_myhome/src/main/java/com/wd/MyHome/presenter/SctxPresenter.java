package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/8/1 15:12
 * @Description：描述信息
 */
public class SctxPresenter extends WDPresenter<IAppRequest> {
    public SctxPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
            File file = (File) args[2];

            // File arg = (File) args[2];
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            builder.addFormDataPart("imagePic",file.getName(),
                    RequestBody.create(MediaType.parse("multipart/octet-stream"),
                            file));
            return iRequest.sctxShow((String)args[0],(String)args[1],builder.build());

        }
}
