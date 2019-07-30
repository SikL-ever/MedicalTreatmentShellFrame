package com.dingtao.common.bean.homepage;

import java.util.List;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/29 13:05
 * @Description：描述信息
 */
public class YsxqBean {

    /**
     * badNum : 0
     * commentList : [{"commentTime":1561447840000,"content":"很好","headPic":"http://172.17.8.100/images/health/user/head_pic/default/default_head_6.jpg","nickName":"hw_LLLYN"}]
     * commentNum : 1
     * doctorId : 2
     * doctorName : 安安
     * doctorReceiveGiftList : []
     * followFlag : 2
     * goodField : 啊啊啊啊
     * imagePic : http://172.17.8.100/images/health/doctor/system_image_pic/system_image2.jpg
     * inauguralHospital : 三甲
     * jobTitle : 主治医师
     * personalProfile : 啊啊啊啊啊啊啊啊
     * praise : 50.00%
     * praiseNum : 1
     * serverNum : 0
     * servicePrice : 500
     */

    private int badNum;
    private int commentNum;
    private int doctorId;
    private String doctorName;
    private int followFlag;
    private String goodField;
    private String imagePic;
    private String inauguralHospital;
    private String jobTitle;
    private String personalProfile;
    private String praise;
    private int praiseNum;
    private int serverNum;
    private int servicePrice;
    private List<CommentListBean> commentList;
    private List<?> doctorReceiveGiftList;

    public int getBadNum() {
        return badNum;
    }

    public void setBadNum(int badNum) {
        this.badNum = badNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getFollowFlag() {
        return followFlag;
    }

    public void setFollowFlag(int followFlag) {
        this.followFlag = followFlag;
    }

    public String getGoodField() {
        return goodField;
    }

    public void setGoodField(String goodField) {
        this.goodField = goodField;
    }

    public String getImagePic() {
        return imagePic;
    }

    public void setImagePic(String imagePic) {
        this.imagePic = imagePic;
    }

    public String getInauguralHospital() {
        return inauguralHospital;
    }

    public void setInauguralHospital(String inauguralHospital) {
        this.inauguralHospital = inauguralHospital;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public int getServerNum() {
        return serverNum;
    }

    public void setServerNum(int serverNum) {
        this.serverNum = serverNum;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public List<CommentListBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentListBean> commentList) {
        this.commentList = commentList;
    }

    public List<?> getDoctorReceiveGiftList() {
        return doctorReceiveGiftList;
    }

    public void setDoctorReceiveGiftList(List<?> doctorReceiveGiftList) {
        this.doctorReceiveGiftList = doctorReceiveGiftList;
    }

    public static class CommentListBean {
        /**
         * commentTime : 1561447840000
         * content : 很好
         * headPic : http://172.17.8.100/images/health/user/head_pic/default/default_head_6.jpg
         * nickName : hw_LLLYN
         */

        private long commentTime;
        private String content;
        private String headPic;
        private String nickName;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
