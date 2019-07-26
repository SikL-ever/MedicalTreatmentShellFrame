package com.dingtao.common.bean.wardBean;

public class TaFaBean {
//     "amount": 10,
//             "collectionNum": 0,
//             "commentNum": 10,
//             "dereleaseTimetail": "初期，起病缓慢者膝关节疼痛不严重，有可持续性隐痛，气温降低时疼痛加重，与气候变化有关，晨起后开始活动，长时间行走，剧烈运动或久坐起立开始走时膝关节疼痛僵硬，稍活动后好转，上、下楼困难，下楼时膝关节发软，易摔倒。蹲起时疼痛，僵硬，严重时，关节酸痛胀痛,跛行走，合并风湿病者关节红肿，畸形，功能受限，伸屈活动有弹响声，部分患者可见关节积液，局部有明显肿胀、压缩现象",
//             "": 1556985600000,
//             "sickCircleId": 7,
//             "title": "急寻：面神经炎的治疗方法"
    public int amount;
    public int collectionNum;
    public int commentNum;
    public String detail;
    public long releaseTime;
    public int sickCircleId;
    public String title;

    public TaFaBean(int amount, int collectionNum, int commentNum, String detail, long releaseTime, int sickCircleId, String title) {
        this.amount = amount;
        this.collectionNum = collectionNum;
        this.commentNum = commentNum;
        this.detail = detail;
        this.releaseTime = releaseTime;
        this.sickCircleId = sickCircleId;
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getSickCircleId() {
        return sickCircleId;
    }

    public void setSickCircleId(int sickCircleId) {
        this.sickCircleId = sickCircleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
