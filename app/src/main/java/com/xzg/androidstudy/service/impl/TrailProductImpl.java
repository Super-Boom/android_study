package com.xzg.androidstudy.service.impl;

import com.xzg.androidstudy.HttpReqActivity;
import com.xzg.androidstudy.data.TrailProductDetail;
import com.xzg.androidstudy.service.TrailProduct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class TrailProductImpl implements TrailProduct {

    @Override
    public Call<TrailProductDetail> getTrailProductDetail() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://47.96.113.94:12500")
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
        TrailProduct trailProduct;
        trailProduct = retrofit.create(TrailProduct.class);
        return trailProduct.getTrailProductDetail();
    }
}
