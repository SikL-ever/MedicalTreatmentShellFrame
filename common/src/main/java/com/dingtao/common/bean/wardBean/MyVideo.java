package com.dingtao.common.bean.wardBean;

public class MyVideo {
//     "createTime": 1564146313000,
//             "duration": 527,
//             "id": 56,
//             "originalUrl": "http://172.17.8.100/video/health/original/js/js1.mp4",
//             "title": "10个健身教练不会告诉你的诀窍",
//             "videoId": 17
    public long createTime;
    public int duration;
    public int id;
    public String originalUrl;
    public String title;
    public int videoId;

    public MyVideo(long createTime, int duration, int id, String originalUrl, String title, int videoId) {
        this.createTime = createTime;
        this.duration = duration;
        this.id = id;
        this.originalUrl = originalUrl;
        this.title = title;
        this.videoId = videoId;
    }

    public long getCreateTime() {

        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}
