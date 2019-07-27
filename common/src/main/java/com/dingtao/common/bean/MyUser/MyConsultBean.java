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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInfold() {
        return infold;
    }

    public void setInfold(int infold) {
        this.infold = infold;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public MyConsultBean(int id, int infold, String title, String thumbnail, long createTime) {
        this.id = id;
        this.infold = infold;
        this.title = title;
        this.thumbnail = thumbnail;
        this.createTime = createTime;
    }
}
