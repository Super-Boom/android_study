package com.xzg.androidstudy.service.impl;

import com.xzg.androidstudy.bean.Products;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class QwProducts implements com.xzg.androidstudy.service.QwProducts {
    private String baseUrl = "http://47.96.113.94:12401";

    @Override
    public Call<Products> getQwProducts() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(FastJsonConverterFactory.create()).build();
        QwProducts qwProducts;
        qwProducts = retrofit.create(QwProducts.class);
        return qwProducts.getQwProducts();
    }
}
