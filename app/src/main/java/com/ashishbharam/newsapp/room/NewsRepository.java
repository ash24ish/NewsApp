package com.ashishbharam.newsapp.room;

/* 
Created by Ashish Bharam on 04-Nov-20 at 11:28 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NewsRepository {

    private NewsRoomDatabase database;
    private LiveData<List<EntityNews>> getAllNewsList;

    public NewsRepository(Application application) {
        database = NewsRoomDatabase.getInstance(application);
        getAllNewsList = database.newsDao().getAllNews();
    }

    public void insert(List<EntityNews> newsList){

        new InsertAsyncTask(database).execute(newsList);
    }

    public LiveData<List<EntityNews>> getGetAllNewsList(){
        return getAllNewsList;
    }

    static class InsertAsyncTask extends AsyncTask<List<EntityNews>, Void, Void>{

        private NewsDao newsDao;
        public InsertAsyncTask (NewsRoomDatabase database){
            newsDao = database.newsDao();
        }
        @Override
        protected Void doInBackground(List<EntityNews>... lists) {
            newsDao.insert(lists[0]);
            return null;
        }
    }

}
