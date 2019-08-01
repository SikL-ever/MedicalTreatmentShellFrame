package com.dingtao.common.bean;

public class MyUserMessage {
    public int id;
    public String content;
    public long   createTime;

    @Override
    public String toString() {
        return "MyUserMessage{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
