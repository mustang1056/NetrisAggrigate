package com.netris.net.service.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("urlType")
    @Expose
    private String urlType;

    @SerializedName("videoUrl")
    @Expose
    private String videoUrl;

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getUrlType() {
        return urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }






}