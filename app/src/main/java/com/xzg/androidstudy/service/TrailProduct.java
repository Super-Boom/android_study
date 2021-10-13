package com.xzg.androidstudy.service;

import com.xzg.androidstudy.data.TrailProductDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TrailProduct {
    @GET("/api/tuitui/getTrialProductDetail")
//    Call<TrailProductDetail> getTrailProductDetail(@Path("user") String user);
    Call<TrailProductDetail> getTrailProductDetail();
}
