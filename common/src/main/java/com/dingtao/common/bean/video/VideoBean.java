package com.dingtao.common.bean.video;

import java.util.List;

/**
 * 佀常勇
 *
 * @Data:2019/7/12 10:09
 * 描述：
 */
public class VideoBean {
    public String abstracts;
    public int buyNum;
    public int categoryId;
    public int duration;
    public int id;
    public String originalUrl;
    public int price;
    public String shearUrl;
    public String title;
    public int whetherBuy;
    public int whetherCollection;

    @Override
    public String toString() {
        return "VideoBean{" +
                "abstracts='" + abstracts + '\'' +
                ", buyNum=" + buyNum +
                ", categoryId=" + categoryId +
                ", duration=" + duration +
                ", id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", price=" + price +
                ", shearUrl='" + shearUrl + '\'' +
                ", title='" + title + '\'' +
                ", whetherBuy=" + whetherBuy +
                ", whetherCollection=" + whetherCollection +
                '}';
    }
}