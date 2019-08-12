package com.dingtao.common.bean.wardBean;

public class MyDoctorBean {
//      "badNum": 0,
//              "departmentId": 2,
//              "departmentName": "骨科",
//              "doctorId": 36,
//              "id": 100,
//              "imagePic": "http://172.17.8.100/images/health/doctor/image_pic/2019-07-31/20190731200154.jpeg",
//              "inauguralHospital": "清华大学附属医院",
//              "jobTitle": "主任",
//              "name": "杜亚星",
//              "number": 20,
//              "praise": "0.00%",
//              "praiseNum": 0b
    public int badNum;
    public int departmentId;
    public String departmentName;
    public int doctorId;
    public int id;
    public String imagePic;
    public String inauguralHospital;
    public String jobTitle;
    public String name;
    public int number;
    public String parise;
    public String praiseNum;

    public MyDoctorBean(int badNum, int departmentId, String departmentName, int doctorId, int id, String imagePic, String inauguralHospital, String jobTitle, String name, int number, String parise, String praiseNum) {
        this.badNum = badNum;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.doctorId = doctorId;
        this.id = id;
        this.imagePic = imagePic;
        this.inauguralHospital = inauguralHospital;
        this.jobTitle = jobTitle;
        this.name = name;
        this.number = number;
        this.parise = parise;
        this.praiseNum = praiseNum;
    }

    public int getBadNum() {
        return badNum;
    }

    public void setBadNum(int badNum) {
        this.badNum = badNum;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getParise() {
        return parise;
    }

    public void setParise(String parise) {
        this.parise = parise;
    }

    public String getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(String praiseNum) {
        this.praiseNum = praiseNum;
    }
}
