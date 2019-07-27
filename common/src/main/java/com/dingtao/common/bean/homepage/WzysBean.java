package com.dingtao.common.bean.homepage;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/24 19:23
 * @Description：描述信息
 */
public class WzysBean {

    /**
     * badNum : 0
     * doctorId : 36
     * doctorName : 杜亚星
     * imagePic : http://172.17.8.100/images/health/doctor/system_image_pic/system_image1.jpg
     * inauguralHospital : 清华大学附属医院
     * jobTitle : 主任
     * praise : 0.00%
     * praiseNum : 0
     * serverNum : 1
     * servicePrice : 500
     */

    private int badNum;
    private int doctorId;
    private String doctorName;
    private String imagePic;
    private String inauguralHospital;
    private String jobTitle;
    private String praise;
    private int praiseNum;
    private int serverNum;
    private int servicePrice;
    private int textcolor = 0xff999999;

    public int getBadNum() {
        return badNum;
    }

    public void setBadNum(int badNum) {
        this.badNum = badNum;
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

    public int getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(int textcolor) {
        this.textcolor = textcolor;
    }
}
