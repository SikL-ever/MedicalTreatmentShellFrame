package com.wd.MyHome.presenter;

import android.util.Log;

import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDApplication;
import com.dingtao.common.core.WDPresenter;
import com.dingtao.common.core.http.IAppRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 佀常勇
 *
 * @Data:2019/7/18 21:26
 * 描述：
 */
public class AddRecordPhotoPresenter extends WDPresenter<IAppRequest> {

    public AddRecordPhotoPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        //builder.addFormDataPart("picture", (String)args[2]);
        List<Object> list = (List<Object>) args[2];
        if (list.size()>1) {
            for (int i = 1; i < list.size(); i++) {
                File file = new File((String) list.get(i));
                builder.addFormDataPart("picture", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"),
                                file));
            }
        }
        return iRequest.addrecordphoto((String)args[0],(String)args[1],builder.build());
    }
}
