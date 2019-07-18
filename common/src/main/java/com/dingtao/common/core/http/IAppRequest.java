package com.dingtao.common.core.http;


import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.homepage.Banner;
import com.dingtao.common.bean.homepage.CjypBean;
import com.dingtao.common.bean.homepage.CjypBean1;
import com.dingtao.common.bean.homepage.DuotiaomuBean;
import com.dingtao.common.bean.homepage.SousuoBean;
import com.dingtao.common.bean.homepage.WzzxBean;
import com.dingtao.common.bean.homepage.YpxqBean;
import com.dingtao.common.bean.homepage.ZhuBean;
import com.dingtao.common.bean.homepage.ZiBean;
import com.dingtao.common.bean.homepage.ZxbkBean;
import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.bean.video.TopBean;
import com.dingtao.common.bean.video.VideoBean;
/*import com.dingtao.common.bean.video.TopBean;
import com.dingtao.common.bean.video.VideoBean;*/

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * @author dingtao
 * @date 2018/12/28 10:00
 * qq:1940870847
 */
public interface IAppRequest {

    //sichangyong-------------------------------------------------------------------别动我的
    //登录
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<Result<LoginBean>> getlogin(@Field("email") String email,
                                           @Field("pwd") String pwd);
    //注册
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<Result> getregister(@Field("email") String email,
                                   @Field("code") String code,
                                   @Field("pwd1") String pwd1,
                                   @Field("pwd2") String pwd2,
                                   @Field("invitationCode") String invitationCode);
    //邮箱
    @FormUrlEncoded
    @POST("user/v1/sendOutEmailCode")
    Observable<Result> getemail(@Field("email") String email);
    //重设密码(忘记密码使用)
    @FormUrlEncoded
    @PUT("user/v1/resetUserPwd")
    Observable<Result> anewpass(@Field("email") String email,
                                @Field("pwd1") String pwd1,
                                @Field("pwd2") String pwd2);
    //1.查询健康讲堂类目
   @GET("user/video/v1/findVideoCategoryList")
    Observable<Result<List<TopBean>>> topdata();
    //根据视频类目查询视频列表
    @GET("user/video/v1/findVideoVoList")
    Observable<Result<List<VideoBean>>> videodata(@Header("userId") String userId,
                                                  @Header("sessionId") String sessionId,
                                                  @Query("categoryId") int categoryId,
                                                  @Query("page") int page,
                                                  @Query("count") int count);
    //sichangyong-------------------------------------------------------------------别动我的

































    @GET("share/v1/bannersShow")
    Observable<Result<List<Banner>>> bannershow();
  /*  @GET("health/share/v1/homePageSearch")
    Observable<> bannershow();*/
  @GET("share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<WzzxBean>>> wzzxshow();
  @GET("share/information/v1/findInformationPlateList")
    Observable<Result<List<ZxbkBean>>> zxbkshow();
    @GET("share/information/v1/findInformationList")
    Observable<Result<List<DuotiaomuBean>>> dtmshow(@Query("plateId") int plateId,@Query("page") int page,@Query("count") int count);
    @GET("share/knowledgeBase/v1/findDiseaseCategory")
    Observable<Result<List<ZiBean>>> zishow(@Query("departmentId") int departmentId);

    @GET("share/knowledgeBase/v1/findDiseaseKnowledge")
    Observable<Result<ZhuBean>> zhushow(@Query("id") int id);
    @GET("share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<Result<List<CjypBean>>> cjypshow();
    @GET("share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<Result<List<CjypBean1>>> cjyp1how(@Query("drugsCategoryId") int drugsCategoryId, @Query("page") int page, @Query("count") int count);
    @GET("share/knowledgeBase/v1/findDrugsKnowledge")
    Observable<Result<YpxqBean>> ypshow(@Query("id") int id);

    //xieqi-------------------------------------------------------------------别动我的
}
