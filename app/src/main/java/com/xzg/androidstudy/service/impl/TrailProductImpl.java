package com.xzg.androidstudy.service.impl;

import android.util.Log;

import com.xzg.androidstudy.data.TrailProductDetail;
import com.xzg.androidstudy.service.TrailProductService;
import com.xzg.androidstudy.service.api.TrailProductRetrofit;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class TrailProductImpl implements TrailProductService {

    private String baseUrl = "";

    @Override
    public TrailProductDetail getTrailProductDetail() {
        try {


            Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(FastJsonConverterFactory.create()).build();
            TrailProductRetrofit trailProductRetrofit;
            trailProductRetrofit = retrofit.create(TrailProductRetrofit.class);
            Call<TrailProductDetail> call = trailProductRetrofit.getTrailProductDetail();
            TrailProductDetail trailProductDetail = call.execute().body();
            Log.d("trailProductDetail---", String.valueOf(trailProductDetail));

            if (trailProductDetail.getCode() == 200) {
                return trailProductDetail;
            }
            Log.d("trailProductDetail", String.valueOf(trailProductDetail));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
