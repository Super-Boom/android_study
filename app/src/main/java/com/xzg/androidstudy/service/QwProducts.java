package com.xzg.androidstudy.service;

import com.xzg.androidstudy.bean.Products;

import retrofit2.Call;
import retrofit2.http.POST;

public interface QwProducts {
    @POST("/api/tuitui_service/v1/getProductList")
    Call<Products> getQwProducts();
}



