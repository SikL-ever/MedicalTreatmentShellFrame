package com.dingtao.common.bean.wardBean;

public class MyDiseaseBean {
//     "collectionNum": 3,
//             "commentNum": 12,
//             "createTime": 1563447295000,
//             "disease": "病症描述",
//             "id": 21,
//             "sickCircleId": 18,
//             "title": "急寻：面神经炎的治疗方法"
    public int collectionNum;
    public int commentNum;
    public long createTime;
    public String disease;
    public int id;
    public int sickCircleId;
    public String title;

    public MyDiseaseBean(int collectionNum, int commentNum, long createTime, String disease, int id, int sickCircleId, String title) {
        this.collectionNum = collectionNum;
        this.commentNum = commentNum;
        this.createTime = createTime;
        this.disease = disease;
        this.id = id;
        this.sickCircleId = sickCircleId;
        this.title = title;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
