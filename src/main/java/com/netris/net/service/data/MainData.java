package com.netris.net.service.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainData {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("sourceDataUrl")
    @Expose
    private String sourceDataUrl;

    @SerializedName("tokenDataUrl")
    @Expose
    private String tokenDataUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceDataUrl() {
        return sourceDataUrl;
    }

    public void setSourceDataUrl(String sourceDataUrl) {
        this.sourceDataUrl = sourceDataUrl;
    }

    public String getTokenDataUrl() {
        return tokenDataUrl;
    }

    public void setTokenDataUrl(String tokenDataUrl) {
        this.tokenDataUrl = tokenDataUrl;
    }


}