package com.ashishbharam.newsapp.viewmodel;

/* 
Created by Ashish Bharam on 04-Nov-20 at 11:53 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ashishbharam.newsapp.room.EntityNews;
import com.ashishbharam.newsapp.room.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepository repository;
    private LiveData<List<EntityNews>> getAllNewsList;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = new NewsRepository(application);
        getAllNewsList = repository.getGetAllNewsList();
    }

    public void insert(List<EntityNews> newsList){
        repository.insert(newsList);
    }

    public LiveData<List<EntityNews>> getGetAllNewsList(){
        return getAllNewsList;
    }
}
