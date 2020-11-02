package com.netris.net.service.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("ttl")
    @Expose
    private String ttl;




}