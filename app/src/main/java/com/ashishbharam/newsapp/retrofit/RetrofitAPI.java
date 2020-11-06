package com.ashishbharam.newsapp.retrofit;

/* 
Created by Ashish Bharam on 17-Oct-20 at 07:23 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import com.ashishbharam.newsapp.pojos.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("allnews")
    Call<DefaultResponse> getResponse();
}
