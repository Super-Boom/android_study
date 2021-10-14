package com.xzg.androidstudy.service.impl;

import com.xzg.androidstudy.data.TrailProductDetail;
import com.xzg.androidstudy.service.TrailProduct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TrailProductImpl implements TrailProduct {

    @Override
    public Call<TrailProductDetail> getTrailProductDetail() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://47.96.113.94:12500").build();
        TrailProduct trailProduct = retrofit.create(TrailProduct.class);
        trailProduct.getTrailProductDetail().enqueue(new Callback<TrailProductDetail>() {
            @Override
            public void onResponse(Call<TrailProductDetail> call, Response<TrailProductDetail> response) {
                if (response != null && response.body() != null) {
                    TrailProductDetail trailProductDetail = response.body();
                }
            }

            @Override
            public void onFailure(Call<TrailProductDetail> call, Throwable t) {

            }
        });
        return null;
    }
}
