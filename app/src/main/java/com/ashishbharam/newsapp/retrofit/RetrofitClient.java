package com.ashishbharam.newsapp.retrofit;

/* 
Created by Ashish Bharam on 17-Oct-20 at 07:16 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL ="http://192.168.1.4/newstest/public/";

    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    private OkHttpClient client;

    private RetrofitClient(){
        client = new OkHttpClient().newBuilder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        }

        public static synchronized RetrofitClient getInstance(){
            if (mInstance == null){
                mInstance = new RetrofitClient();
            }
                return mInstance;
        }

        public RetrofitAPI getRetrofitApi() {
            return retrofit.create(RetrofitAPI.class);
        }
}

