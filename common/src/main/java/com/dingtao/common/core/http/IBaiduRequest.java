package com.dingtao.common.core.http;

import com.dingtao.common.bean.BDResult;
import com.dingtao.common.bean.MyUser.UserRecordBean;
import com.dingtao.common.bean.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IBaiduRequest {
    //用户查看自己的档案
    @GET("user/verify/v1/findUserArchives")
    Observable<BDResult<UserRecordBean>> myuserrecord(@Header("userId") String userId,
                                                      @Header("sessionId") String sessionId);
}
