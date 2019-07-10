package com.dingtao.common.core.http;


import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.homepage.Banner;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IAppRequest {
    @GET("share/v1/bannersShow")
    Observable<Result<List<Banner>>> bannershow();
}
