package com.wpy.news.model;

/**
 * Created by X230 on 2017/4/25.
 */

public class AppUpDate {
    /**
     * action : app_update
     * code : 200
     * url : http://cs.qiyew.com/apk/jushi1.1.7.apk
     * version : 1.0
     * version_must : 0
     * platform : android
     */

    private String action;
    private String code;
    private String url;
    private String version;
    private String version_must;
    private String platform;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion_must() {
        return version_must;
    }

    public void setVersion_must(String version_must) {
        this.version_must = version_must;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
