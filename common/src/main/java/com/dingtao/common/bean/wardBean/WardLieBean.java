package com.dingtao.common.bean.wardBean;

public class WardLieBean {
//     "amount": 10,
//             "collectionNum": 1,
//             "commentNum": 10,
//             "detail": "详情",
//             "releaseTime": 1555948800000,
//             "sickCircleId": 15,
//             "title": "急寻：面神经炎的治疗方法"
    public int amount;
    public int collectionNum;
    public int commentNum;
    public String detail;
    public long releaseTime;
    public int sickCircleId;
    public String title;

    public WardLieBean(int amount, int collectionNum, int commentNum, String detail, long releaseTime, int sickCircleId, String title) {
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
