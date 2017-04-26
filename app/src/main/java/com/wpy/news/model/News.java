package com.wpy.news.model;

/**
 * Created by X230 on 2017/4/18.
 */

public class News {
    /**
     * ctime : 2017-04-11 19:56
     * title : 苏州一商业楼盗水80万吨 律师：涉事者或判无期
     * description : 搜狐社会
     * picUrl : http://photocdn.sohu.com/20170411/Img487785831_ss.jpeg
     * url : http://news.sohu.com/20170411/n487785830.shtml
     */

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
