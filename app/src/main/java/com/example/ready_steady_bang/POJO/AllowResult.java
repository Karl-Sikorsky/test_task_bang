package com.example.ready_steady_bang.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllowResult {
    @Override
    public String toString() {
        return "AllowResult{" +
                "allow=" + allow +
                '}';
    }

    @SerializedName("allow")
    @Expose
    private Boolean allow;

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }
}
