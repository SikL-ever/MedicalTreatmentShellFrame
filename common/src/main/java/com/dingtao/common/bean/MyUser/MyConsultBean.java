package com.dingtao.common.bean.MyUser;

/**
 * 佀常勇
 *
 * @Data:2019/7/20 10:49
 * 描述：
 */
public class MyConsultBean {
    public int id;
    public int infold;
    public String title;
    public String thumbnail;
    public long createTime;

    @Override
    public String toString() {
        return "MyConsultBean{" +
                "id=" + id +
                ", infold=" + infold +
                ", title='" + title + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
