package com.example.ready_steady_bang.network;

import com.example.ready_steady_bang.POJO.AllowRequest;
import com.example.ready_steady_bang.POJO.AllowResult;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkService {

    @POST("q")
    Single<AllowResult> getAllowResult(@Body AllowRequest allowRequest);
}
