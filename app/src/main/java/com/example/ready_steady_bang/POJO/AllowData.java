package com.example.ready_steady_bang.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllowData {
    public String getAllow() {
        return allow;
    }

    public AllowData(String allow) {
        this.allow = allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    @SerializedName("allow")

    @Expose
    private String allow;
}
