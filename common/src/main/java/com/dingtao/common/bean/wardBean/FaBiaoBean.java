package com.dingtao.common.bean.wardBean;

public class FaBiaoBean {
//     "title": "急寻：面神经炎的治疗方法",
//             "departmentId": 9,
//             "disease": "有大半年了，胸口骨头疼痛，按压会痛，早上起床或走路时不会痛，久坐后活动肩膀或扩胸有时会有咯吱的轻微响声。我的工作是平面设计，一般电脑前面坐一天，活动较少。最近这十来天疼痛感比之前稍重一点。 ",
//             "detail": "详初期，起病缓慢者膝关节疼痛不严重，有可持续性隐痛，气温降低时疼痛加重，与气候变化有关，晨起后开始活动，长时间行走，剧烈运动或久坐起立开始走时膝关节疼痛僵硬，稍活动后好转，上、下楼困难，下楼时膝关节发软，易摔倒。蹲起时疼痛，僵硬，严重时，关节酸痛胀痛,跛行走，合并风湿病者关节红肿，畸形，功能受限，伸屈活动有弹响声，部分患者可见关节积液，局部有明显肿胀、压缩现象",
//             "treatmentEndTime": 1536681600000,
//             "treatmentHospital": "治疗医院",
//             "treatmentProcess": "治疗过程描述",
//             "treatmentStartTime": 1486051200000,
//             "amount": 10
    public String title;
    public int departmentId;
    public String disease;
    public String detail;
    public long treatmentEndTime;
    public String treatmentHospital;
    public String treatmentProcess;
    public long treatmentStartTime;

    public FaBiaoBean(String title, int departmentId, String disease, String detail, long treatmentEndTime, String treatmentHospital, String treatmentProcess, long treatmentStartTime) {
        this.title = title;
        this.departmentId = departmentId;
        this.disease = disease;
        this.detail = detail;
        this.treatmentEndTime = treatmentEndTime;
        this.treatmentHospital = treatmentHospital;
        this.treatmentProcess = treatmentProcess;
        this.treatmentStartTime = treatmentStartTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getTreatmentEndTime() {
        return treatmentEndTime;
    }

    public void setTreatmentEndTime(long treatmentEndTime) {
        this.treatmentEndTime = treatmentEndTime;
    }

    public String getTreatmentHospital() {
        return treatmentHospital;
    }

    public void setTreatmentHospital(String treatmentHospital) {
        this.treatmentHospital = treatmentHospital;
    }

    public String getTreatmentProcess() {
        return treatmentProcess;
    }

    public void setTreatmentProcess(String treatmentProcess) {
        this.treatmentProcess = treatmentProcess;
    }

    public long getTreatmentStartTime() {
        return treatmentStartTime;
    }

    public void setTreatmentStartTime(long treatmentStartTime) {
        this.treatmentStartTime = treatmentStartTime;
    }
}

