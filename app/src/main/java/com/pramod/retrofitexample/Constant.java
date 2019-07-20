package com.pramod.retrofitexample;

import com.pramod.retrofitexample.interfaces.JsonPlaceHolderApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Constant {

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonPlaceHolderApi jsonPlaceHolderApi= retrofit.create(JsonPlaceHolderApi.class);
}
