package com.dingtao.common.bean.homepage;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/7/16 14:09
 * @Description：描述信息
 */
public class DuotiaomuBean {

    /**
     * id : 1
     * plateId : 1
     * releaseTime : 1555035309000
     * source : @weidu
     * thumbnail : https://jkcdn.pajk.com.cn/image/T1slZcBvEg1RCvBVdK;https://jkcdn.pajk.com.cn/image/T1YYVOBvYT1RCvBVdK;https://jkcdn.pajk.com.cn/image/T1mRDSB7xT1RCvBVdK
     * title : 春季预防三高，预防心脑血管疾病，不得不提到的三个“笋”子！
     */

    private int id;
    private int plateId;
    private long releaseTime;
    private String source;
    private String thumbnail;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlateId() {
        return plateId;
    }

    public void setPlateId(int plateId) {
        this.plateId = plateId;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
