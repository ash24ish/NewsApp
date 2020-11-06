package com.ashishbharam.newsapp.room;

/* 
Created by Ashish Bharam on 04-Nov-20 at 12:37 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {EntityNews.class}, version = 1)
public abstract class NewsRoomDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "NewsDatabase";

    public abstract NewsDao newsDao();

    private static NewsRoomDatabase mInstance;

    public static NewsRoomDatabase getInstance(final Context context) {
        if (mInstance == null) {
            synchronized (NewsRoomDatabase.class) {
                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(context,
                            NewsRoomDatabase.class,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return mInstance;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewsDao newsDao;
        PopulateAsyncTask(NewsRoomDatabase database){
            newsDao = database.newsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            newsDao.deleteAll();
            return null;
        }
    }

}
