package com.dingtao.common.bean.MyUser;

public class MyUserSuggestBean {
    public int releaseUserId;
    public String releaseUserNickName;
    public String releaseUserHeadPic;
    public String title;
    public String disease;
    public long adoptTime;
    public String content;

    @Override
    public String toString() {
        return "MyUserSuggestBean{" +
                "releaseUserId=" + releaseUserId +
                ", releaseUserNickName='" + releaseUserNickName + '\'' +
                ", releaseUserHeadPic='" + releaseUserHeadPic + '\'' +
                ", title='" + title + '\'' +
                ", disease='" + disease + '\'' +
                ", adoptTime=" + adoptTime +
                ", content='" + content + '\'' +
                '}';
    }
}
