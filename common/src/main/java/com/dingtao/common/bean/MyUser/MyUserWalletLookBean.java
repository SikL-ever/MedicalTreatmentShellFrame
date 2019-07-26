package com.dingtao.common.bean.MyUser;

public class MyUserWalletLookBean {
    public String changeNum;
    public long createTime;
    public int direction;
    public String remark;
    public String type;

    @Override
    public String toString() {
        return "MyUserWalletLookBean{" +
                "changeNum='" + changeNum + '\'' +
                ", createTime=" + createTime +
                ", direction='" + direction + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
