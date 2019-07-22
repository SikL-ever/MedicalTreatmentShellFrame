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
    public int id;
    public int collectionNum;
    public int commentNum;
    public String detail;
    public String disease;
    public long releaseTime;
    public int sickCircleId;
    public String title;
    public long createTime;

    public WardLieBean(int amount, int id, int collectionNum, int commentNum, String detail, String disease, long releaseTime, int sickCircleId, String title, long createTime) {
        this.amount = amount;
        this.id = id;
        this.collectionNum = collectionNum;
        this.commentNum = commentNum;
        this.detail = detail;
        this.disease = disease;
        this.releaseTime = releaseTime;
        this.sickCircleId = sickCircleId;
        this.title = title;
        this.createTime = createTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
