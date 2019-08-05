package com.dingtao.common.core.http;


import com.dingtao.common.bean.MyUser.CxxxBean;
import com.dingtao.common.bean.MyUser.MyCollectVideoBean;
import com.dingtao.common.bean.MyUser.MyConsultBean;
import com.dingtao.common.bean.MyUser.MyUserHistoryBean;
import com.dingtao.common.bean.MyUser.MyUserLookNewinquiryBean;
import com.dingtao.common.bean.MyUser.MyUserSuggestBean;
import com.dingtao.common.bean.MyUser.MyUserWalletLookBean;
import com.dingtao.common.bean.MyUser.UserRecordBean;
import com.dingtao.common.bean.MyUserMessage;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.homepage.Banner;
import com.dingtao.common.bean.homepage.CjypBean;
import com.dingtao.common.bean.homepage.CjypBean1;
import com.dingtao.common.bean.homepage.DuotiaomuBean;
import com.dingtao.common.bean.homepage.RmssBean;
import com.dingtao.common.bean.homepage.SousuoBean;
import com.dingtao.common.bean.homepage.WzysBean;
import com.dingtao.common.bean.homepage.WzzxBean;
import com.dingtao.common.bean.homepage.XqBean;
import com.dingtao.common.bean.homepage.YpxqBean;
import com.dingtao.common.bean.homepage.YsxqBean;
import com.dingtao.common.bean.homepage.ZhuBean;
import com.dingtao.common.bean.homepage.ZiBean;
import com.dingtao.common.bean.homepage.ZxbkBean;
import com.dingtao.common.bean.login.LoginBean;
import com.dingtao.common.bean.video.DanBean;
import com.dingtao.common.bean.video.TopBean;
import com.dingtao.common.bean.video.VideoBean;
import com.dingtao.common.bean.wardBean.BingzhengBean;
import com.dingtao.common.bean.wardBean.List_xiang_Bean;
import com.dingtao.common.bean.wardBean.MyVideo;
import com.dingtao.common.bean.wardBean.Ping_lie_Bean;
import com.dingtao.common.bean.wardBean.SeachBean;
import com.dingtao.common.bean.wardBean.TaFaBean;
import com.dingtao.common.bean.wardBean.TabBean;
import com.dingtao.common.bean.wardBean.WardLieBean;
/*import com.dingtao.common.bean.video.TopBean;
import com.dingtao.common.bean.video.VideoBean;*/

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

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
    //购买视频
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
    @POST("user/verify/v1/addSign")
    Observable<Result> usersign(@Header("userId") String userId,
                                @Header("sessionId") String sessionId);


    //用户查看自己的档案
    @GET("user/verify/v1/findUserArchives")
    Observable<Result<UserRecordBean>> myuserrecord(@Header("userId") String userId,
                                                    @Header("sessionId") String sessionId);

    //23.删除用户档案
    @DELETE("user/verify/v1/deleteUserArchives")
    Observable<Result> deletemyuserrecord(@Header("userId") String userId,
                                          @Header("sessionId") String sessionId,
                                          @Query("archivesId") int archivesId);
    //.添加用户档案
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("user/verify/v1/addUserArchives")
    Observable<Result> addrecord(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Body RequestBody body);
    //上传图片
    //上传用户档案相关图片
    //@Multipart
    @POST("/user/verify/v1/modifyHeadPic")
    Observable<Result> addrecordphoto(@Header("userId") String userId,
                                      @Header("sessionId") String sessionId,
                                      @Body MultipartBody body);
    //修改我的档案
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PUT("user/verify/v1/updateUserArchives")
    Observable<Result> uprecord(@Header("userId") String userId,
                                @Header("sessionId") String sessionId,
                                @Body RequestBody body);
    //.查询用户资讯收藏列表
    @GET("user/verify/v1/findUserInfoCollectionList")
    Observable<Result<List<MyConsultBean>>> mycollectconsult(@Header("userId") String userId,
                                                             @Header("sessionId") String sessionId,
                                                             @Query("page") int page,
                                                             @Query("count") int count);

    //.用户收藏病友圈列表
    @GET("user/verify/v1/findUserSickCollectionList")
    Observable<Result<List<WardLieBean>>> mycollectbing(@Header("userId") String userId,
                                                        @Header("sessionId") String sessionId,
                                                        @Query("page") int page,
                                                        @Query("count") int count);

    //.用户收藏健康课堂视频列表
    @GET("user/verify/v1/findVideoCollectionList")
    Observable<Result<List<MyCollectVideoBean>>> mycollectvideo(@Header("userId") String userId,
                                                                          @Header("sessionId") String sessionId,
                                                                          @Query("page") int page,
                                                                          @Query("count") int count);
    //取消收藏将康视频
    @DELETE("user/verify/v1/cancelVideoCollection")
    Observable<Result> deletemyuservideo(@Header("userId") String userId,
                                          @Header("sessionId") String sessionId,
                                          @Query("videoId") int videoId);
    //取消收藏病友圈
   @DELETE("user/verify/v1/cancelSickCollection")
   Observable<Result> deletemybing(@Header("userId") String userId,
                                   @Header("sessionId") String sessionId,
                                   @Query("sickCircleId") int sickCircleId);

    //取消收藏咨询
    @DELETE("user/verify/v1/cancelInfoCollection")
    Observable<Result> deletemyconsult(@Header("userId") String userId,
                                       @Header("sessionId") String sessionId,
                                       @Query("infoId") int infoId);

   //进行查询用户消费记录
   @GET("user/verify/v1/findUserConsumptionRecordList")
   Observable<Result<List<MyUserWalletLookBean>>> myuserwalletlook(@Header("userId") String userId,
                                                                   @Header("sessionId") String sessionId,
                                                                   @Query("page") int page,
                                                                   @Query("count") int count);
   //查询用户被采纳的建议
   @GET("user/verify/v1/findMyAdoptedCommentList")
   Observable<Result<List<MyUserSuggestBean>>> myusersuggest(@Header("userId") String userId,
                                                             @Header("sessionId") String sessionId,
                                                             @Query("page") int page,
                                                             @Query("count") int count);
    //6.上传头像
    @POST("user/verify/v1/modifyHeadPic")
    Observable<Result> updatahead(@Header("userId") String userId,
                                  @Header("sessionId") String sessionId,
                                  @Body MultipartBody body);

    //查询消息
    //.查询用户系统通知列表
    //接口地址：http://172.17.8.100/health/
    @GET("user/verify/v1/findSystemNoticeList")
    Observable<Result<List<MyUserMessage>>> myusersystemMessage(@Header("userId") String userId,
                                                          @Header("sessionId") String sessionId,
                                                          @Query("page") int page,
                                                          @Query("count") int count);
    //问诊通知列表
    @GET("user/verify/v1/findInquiryNoticeList")
    Observable<Result<List<MyUserMessage>>> myuserinquiryMessage(@Header("userId") String userId,
                                                                @Header("sessionId") String sessionId,
                                                                @Query("page") int page,
                                                                @Query("count") int count);
    //H币消费通知
    @GET("user/verify/v1/findHealthyCurrencyNoticeList")
    Observable<Result<List<MyUserMessage>>> myuserHMessage(@Header("userId") String userId,
                                                                @Header("sessionId") String sessionId,
                                                                @Query("page") int page,
                                                                @Query("count") int count);
    //咨询医生
    @PUT("user/inquiry/verify/v1/consultDoctor")
    Observable<Result> consultAdoctor(@Header("userId") String userId,
                                      @Header("sessionId") String sessionId,
                                      @Query("doctorId") int doctorId);
    //.问诊-发送消息
    @FormUrlEncoded
    @POST("user/video/verify/v1/addVideoComment")
    Observable<Result> sendmessage(@Header("userId") String userId,
                                             @Header("sessionId") String sessionId,
                                             @Field("inquiryId") int inquiryId,
                                             @Field("msgContent") String msgContent,
                                             @Field("content") String content,
                                             @Field("type") int type,
                                                @Field("doctorId") int doctorId);
    //sichangyong-------------------------------------------------------------------别动我的


    /*==========================================LIFANGXIAN====================================================*/
    //科室
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<Result<List<TabBean>>> tab();

    //列表详情
    @GET("user/sickCircle/v1/findSickCircleList")
    Observable<Result<List<WardLieBean>>> wardLie(@Query("departmentId") int departmentId,
                                                  @Query("page") int page,
                                                  @Query("count") int count);

    //搜索病友病症
    @GET("user/sickCircle/v1/searchSickCircle")
    Observable<Result<List<SeachBean>>> seach(@Query("keyWord") String keyWord);

    //病友圈列表详情
    @GET("user/sickCircle/v1/findSickCircleInfo")
    Observable<Result<List_xiang_Bean>> ListXiang(@Header("userId") String userId,
                                                  @Header("sessionId") String sessionId,
                                                  @Query("sickCircleId") int sickCircleId);

    //评论列表
    @GET("user/sickCircle/v1/findSickCircleCommentList")
    Observable<Result<List<Ping_lie_Bean>>> ping(@Header("userId") String userId,
                                                 @Header("sessionId") String sessionId,
                                                 @Query("sickCircleId") int sickCircleId,
                                                 @Query("page") int page,
                                                 @Query("count") int count);

    //评论
    @FormUrlEncoded
    @POST("user/sickCircle/verify/v1/publishComment")
    Observable<Result> pinglun(@Header("userId") String userId,
                               @Header("sessionId") String sessionId,
                               @Field("sickCircleId") int sickCircleId,
                               @Field("content") String content);

    @GET("user/sickCircle/v1/findPatientSickCircleList")
    Observable<Result<List<TaFaBean>>> tafa(@Query("patientUserId") int patientUserId,
                                            @Query("page") int page,
                                            @Query("count") int count);

    //发表病友圈
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("user/sickCircle/verify/v1/publishSickCircle")
    Observable<Result> fabiao(@Header("userId") String userId,
                              @Header("sessionId") String sessionId,
                              @Body RequestBody body);

    @FormUrlEncoded
    //采纳
    @PUT("user/sickCircle/verify/v1/adoptionProposal")
    Observable<Result> collect(@Header("userId") String userId,
                               @Header("sessionId") String sessionId,
                               @Field("commentId") int commentId,
                               @Field("sickCircleId") int sickCircleId);

    //点赞
    @FormUrlEncoded
    @PUT("user/sickCircle/verify/v1/expressOpinion")
    Observable<Result> zan(@Header("userId") String userId,
                           @Header("sessionId") String sessionId,
                           @Field("commentId") int commentId,
                           @Field("opinion") int opinion);

    //病症
    @GET("share/knowledgeBase/v1/findDiseaseCategory")
    Observable<Result<List<BingzhengBean>>> binzheng(@Query("departmentId") int departmentId);

    //收藏
    @FormUrlEncoded
    @POST("user/verify/v1/addUserSickCollection")
    Observable<Result> shoucang(@Header("userId") String userId,
                                @Header("sessionId") String sessionId,
                                @Field("sickCircleId") int sickCircleId);
    //发表图片
    @Multipart
    @POST("user/sickCircle/verify/v1/uploadSickCirclePicture")
    Observable<Result> image(@Header("userId") String userId,
                             @Header("sessionId") String sessionId,
                             @Field("sickCircleId") int sickCircleId,
                             @Part MultipartBody.Part[] picture);
    //我的视频
    @GET("user/verify/v1/findUserVideoBuyList")
    Observable<Result<List<MyVideo>>> videos(@Header("userId") String userId,
                                             @Header("sessionId") String sessionId,
                                             @Query("page") int page,
                                             @Query("count") int count
    );
    //删除我购买的视频
    @DELETE("user/verify/v1/deleteVideoBuy")
    Observable<Result> del(@Header("userId") String userId,
                           @Header("sessionId") String sessionId,
                           @Query("videoId") int page);
    //我的关注

    /*==========================================LIFANGXIAN====================================================*/


    @GET("share/v1/bannersShow")
    Observable<Result<List<Banner>>> bannershow();
    @GET("share/v1/homePageSearch")
    Observable<Result<SousuoBean>> sousuoshow(@Query("keyWord") String keyWord);
    @GET("share/v1/popularSearch")
    Observable<Result<List<RmssBean>>> rmssshow();
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
    @GET("share/information/v1/findInformation")
    Observable<Result<XqBean>> zxxqshow(@Query("infoId") int infoId,@Header("userId") String userId, @Header("sessionId") String sessionId);
    @POST("user/verify/v1/addInfoCollection")
    Observable<Result> shoucangshow(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("infoId") int infoId);
    @DELETE("user/verify/v1/cancelInfoCollection")
    Observable<Result> qxscshow(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("infoId") int infoId);
    @GET("user/inquiry/v1/findDoctorList")
    Observable<Result<List<WzysBean>>> yslbShow(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("deptId") int deptId, @Query("condition") int condition, @Query("sortBy") int sortBy, @Query("page") int page, @Query("count") int count);
    @GET("user/verify/v1/findUserWallet")
    Observable<Result<Double>> moneyShow(@Header("userId") String userId, @Header("sessionId") String sessionId);
    @GET("user/inquiry/v1/findDoctorInfo")
    Observable<Result<YsxqBean>> ysxqShow(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("doctorId") int doctorId);
    @POST("user/inquiry/verify/v1/followDoctor")
    Observable<Result> gzysShow(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("doctorId") int doctorId);
    @DELETE("user/inquiry/verify/v1/cancelFollow")
    Observable<Result> qxysShow(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("doctorId") int doctorId);
    @POST("user/verify/v1/modifyHeadPic")
    Observable<Result> sctxShow(@Header("userId") String userId, @Header("sessionId") String sessionId, @Body MultipartBody body);
    @PUT("user/verify/v1/modifyNickName")
    Observable<Result> xgncShow(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("nickName") String nickName);
    @PUT("user/verify/v1/updateUserSex")
    Observable<Result> sexShow(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("sex") int sex);
    @GET("user/verify/v1/getUserInfoById")
    Observable<Result<CxxxBean>> yhxxShow(@Header("userId") String userId, @Header("sessionId") String sessionId);
    @PUT("user/verify/v1/perfectUserInfo")
    Observable<Result> nstShow(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("age") int age,@Query("height") int height,@Query("weight") int weight);
    //xieqi-------------------------------------------------------------------别动我的
}
