package com.ashishbharam.newsapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/*
Created by Ashish Bharam on 04-Nov-20 at 12:30 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/
@Dao
public interface NewsDao {
    @Query("SELECT * FROM news_table")
    LiveData<List<EntityNews>> getAllNews();

  /*  @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityNews news);*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<EntityNews> allNewsList);

    @Query("DELETE FROM news_table")
    void deleteAll();
}
