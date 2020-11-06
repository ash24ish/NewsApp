package com.ashishbharam.newsapp.pojos;

/* 
Created by Ashish Bharam on 17-Oct-20 at 07:51 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import com.ashishbharam.newsapp.room.EntityNews;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DefaultResponse {
    String error;
    @SerializedName("news")
    @Expose
    List<EntityNews> response;

    public String getError() {
        return error;
    }

    public List<EntityNews> getResponse() {
        return response;
    }
}
