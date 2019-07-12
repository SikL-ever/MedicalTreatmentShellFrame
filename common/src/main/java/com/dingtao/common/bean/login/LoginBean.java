package com.dingtao.common.bean.login;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 佀常勇
 *
 * @Data:2019/7/11 14:59
 * 描述：
 */
@Entity
public class LoginBean {
    @Id
    public String id;
    public String sessionId;
    public String age;
    public String email;
    public String headPic;
    public String invitationCode;
    public String jiGuangPwd;
    public String nickName;
    public String sex;
    public String userName;
    public String weight;
    public int whetherBingWeChat;
    public int ttt=1;//登陆状态1是不登录状态2是登录状态
    @Generated(hash = 817621893)
    public LoginBean(String id, String sessionId, String age, String email,
            String headPic, String invitationCode, String jiGuangPwd,
            String nickName, String sex, String userName, String weight,
            int whetherBingWeChat, int ttt) {
        this.id = id;
        this.sessionId = sessionId;
        this.age = age;
        this.email = email;
        this.headPic = headPic;
        this.invitationCode = invitationCode;
        this.jiGuangPwd = jiGuangPwd;
        this.nickName = nickName;
        this.sex = sex;
        this.userName = userName;
        this.weight = weight;
        this.whetherBingWeChat = whetherBingWeChat;
        this.ttt = ttt;
    }
    @Generated(hash = 1112702939)
    public LoginBean() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSessionId() {
        return this.sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getHeadPic() {
        return this.headPic;
    }
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
    public String getInvitationCode() {
        return this.invitationCode;
    }
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
    public String getJiGuangPwd() {
        return this.jiGuangPwd;
    }
    public void setJiGuangPwd(String jiGuangPwd) {
        this.jiGuangPwd = jiGuangPwd;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getWeight() {
        return this.weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public int getWhetherBingWeChat() {
        return this.whetherBingWeChat;
    }
    public void setWhetherBingWeChat(int whetherBingWeChat) {
        this.whetherBingWeChat = whetherBingWeChat;
    }
    public int getTtt() {
        return this.ttt;
    }
    public void setTtt(int ttt) {
        this.ttt = ttt;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "id='" + id + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", headPic='" + headPic + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                ", jiGuangPwd='" + jiGuangPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex='" + sex + '\'' +
                ", userName='" + userName + '\'' +
                ", weight='" + weight + '\'' +
                ", whetherBingWeChat=" + whetherBingWeChat +
                ", ttt=" + ttt +
                '}';
    }
}
