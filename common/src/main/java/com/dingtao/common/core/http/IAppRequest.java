package com.dingtao.common.core.http;


import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.homepage.Banner;
import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.bean.video.DanBean;
import com.dingtao.common.bean.video.TopBean;
import com.dingtao.common.bean.video.VideoBean;
import com.dingtao.common.bean.wardBean.SeachBean;
import com.dingtao.common.bean.wardBean.TabBean;
import com.dingtao.common.bean.wardBean.WardLieBean;
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
    // 查询视频评论列表
    @GET("user/video/v1/findVideoCommentList")
    Observable<Result<List<DanBean>>> videodan(@Query("videoId") int videoId);
    // 查询我的钱包
    @GET("user/verify/v1/findUserWallet")
    Observable<Result> videogetprice(@Header("userId") String userId,
                                     @Header("sessionId") String sessionId);
    // 查询我的钱包
    @FormUrlEncoded
    @POST("user/video/verify/v1/videoBuy")
    Observable<Result> videobuy(@Header("userId") String userId,
                                @Header("sessionId") String sessionId,
                                @Field("videoId") int videoId,
                                @Field("price") int price);
    //健康课堂视频收藏
    @FormUrlEncoded
    @POST("user/video/verify/v1/addUserVideoCollection")
    Observable<Result> videocollect(@Header("userId") String userId,
                                    @Header("sessionId") String sessionId,
                                    @Field("videoId") int videoId);
    //发表视频评论（弹幕）
    @FormUrlEncoded
    @POST("user/video/verify/v1/addVideoComment")
    Observable<Result> videosendbulletscreen(@Header("userId") String userId,
                                                @Header("sessionId") String sessionId,
                                                @Field("videoId") int videoId,
                                             @Field("content") String content);
    //sichangyong-----------------------------------------我的页面
    //用户签到
    @FormUrlEncoded
    @POST("user/verify/v1/addSign")
    Observable<Result> usersign(@Header("userId") String userId,
                                @Header("sessionId") String sessionId);
    //用户查看自己的档案
    @GET("user/verify/v1/findUserArchives")
    Observable<Result> myuserrecord(@Header("userId") String userId,
                                    @Header("sessionId") String sessionId);

    //sichangyong-------------------------------------------------------------------别动我的




    /*==========================================LIFANGXIAN====================================================*/
    //科室
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<TabBean>>> tab();
    //列表详情
    @GET("user/sickCircle/v1/findSickCircleList?departmentId=7&page=1&count=10")
    Observable<Result<List<WardLieBean>>> wardLie(@Query("departmentId") int departmentId, @Query("page") int page, @Query("count") int count);
    //搜索病友病症
    @GET("user/sickCircle/v1/searchSickCircle")
    Observable<Result<List<SeachBean>>> seach(@Query("keyWord") String keyWord);

    /*==========================================LIFANGXIAN====================================================*/





























    @GET("share/v1/bannersShow")
    Observable<Result<List<Banner>>> bannershow();
    //xieqi-------------------------------------------------------------------别动我的
}
