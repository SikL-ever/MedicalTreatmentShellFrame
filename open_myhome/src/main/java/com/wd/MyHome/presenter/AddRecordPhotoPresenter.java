package com.wd.MyHome.presenter;

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

    private List<String> path;
    private List<File> fileList;
    File file1;
    public AddRecordPhotoPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(Object... args) {

        /*MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        builder.addFormDataPart("picture", (String)args[2]);
        List<Object> list = (List<Object>) args[2];
        if (list.size()>1) {
            for (int i = 1; i < list.size(); i++) {
                File file = new File((String) list.get(i));
                builder.addFormDataPart("picture", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"),
                                file));
            }
        }*/
        if (path != null){
            path.clear();
        }
        path = (List<String>) args[3];
        fileList = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            file1 = new File(path.get(i));
            fileList.add(file1);
        }
        MultipartBody.Part[] parts = new MultipartBody.Part[path.size()];
        int index = 0;
        for (File file : fileList) {
            //File newFile = CompressHelper.getDefault(WDApplication.getContext()).compressToFile(file);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part image = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
            parts[index] = image;
            index++;
        }
        if (parts.length<1){
            return null;
        }
        return iRequest.addrecordphoto((String)args[0],(String)args[1], parts);
    }
}
