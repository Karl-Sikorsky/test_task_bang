package com.example.ready_steady_bang.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllowRequest {
    public AllowRequest(String token, AllowData data) {
        this.token = token;
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AllowData getData() {
        return data;
    }

    public void setData(AllowData data) {
        this.data = data;
    }

    @SerializedName("token")
    @Expose

    private String token;
    @SerializedName("data")
    @Expose
    private AllowData data;

}
