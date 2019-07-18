package com.dingtao.common.bean.wardBean;

public class Ping_lie_Bean {
//    "commentId": 162,
//            "commentTime": 1563331885000,
//            "commentUserId": 6,
//            "content": "建议内容",
//            "headPic": "http://172.17.8.100/images/health/user/head_pic/2019-06-25/20190625144828.jpg",
//            "nickName": "陈小胖",
//            "opinion": 0,
//            "opposeNum": 0,
//            "supportNum": 0,
//            "whetherDoctor": 2
    public int commentId;
    public long commentTime;
    public int commentUserId;
    public String content;
    public String headPic;
    public String nickName;
    public int opinion;
    public int opposeNum;
    public int supportNum;
    public int whetherDoctor;

    public Ping_lie_Bean(int commentId, long commentTime, int commentUserId, String content, String headPic, String nickName, int opinion, int opposeNum, int supportNum, int whetherDoctor) {
        this.commentId = commentId;
        this.commentTime = commentTime;
        this.commentUserId = commentUserId;
        this.content = content;
        this.headPic = headPic;
        this.nickName = nickName;
        this.opinion = opinion;
        this.opposeNum = opposeNum;
        this.supportNum = supportNum;
        this.whetherDoctor = whetherDoctor;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(long commentTime) {
        this.commentTime = commentTime;
    }

    public int getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(int commentUserId) {
        this.commentUserId = commentUserId;
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

    public int getOpinion() {
        return opinion;
    }

    public void setOpinion(int opinion) {
        this.opinion = opinion;
    }

    public int getOpposeNum() {
        return opposeNum;
    }

    public void setOpposeNum(int opposeNum) {
        this.opposeNum = opposeNum;
    }

    public int getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(int supportNum) {
        this.supportNum = supportNum;
    }

    public int getWhetherDoctor() {
        return whetherDoctor;
    }

    public void setWhetherDoctor(int whetherDoctor) {
        this.whetherDoctor = whetherDoctor;
    }
}
