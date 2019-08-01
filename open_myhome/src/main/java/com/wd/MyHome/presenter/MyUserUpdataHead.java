package com.wd.MyHome.presenter;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyUserUpdataHead extends WDPresenter<IAppRequest> {

    public MyUserUpdataHead(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {
       /* MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        builder.addFormDataPart("image", String.valueOf(args[2]));*/



        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        File file = new File(String.valueOf(args[2]));
        builder.addFormDataPart("image", file.getName(),
                RequestBody.create(MediaType.parse("multipart/octet-stream"), file));
        /*List<Object> list = (List<Object>) args[2];
        if (list.size()>1) {
            for (int i = 1; i < list.size(); i++) {
                File file = new File((String) list.get(i));
                builder.addFormDataPart("image", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"),
                                file));
            }
        }*/
        return iRequest.updatahead((String) args[0],(String) args[1],builder.build());
    }
}
