package com.ashishbharam.newsapp.adapter;

/* 
Created by Ashish Bharam on 17-Oct-20 at 11:05 PM.
Copyright (c) 2020 Ashish Bharam. All rights reserved.
*/

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashishbharam.newsapp.R;
import com.ashishbharam.newsapp.pojos.NewsResponse;
import com.ashishbharam.newsapp.room.EntityNews;

import java.util.ArrayList;
import java.util.List;

public class NewsRecycler extends RecyclerView.Adapter<NewsRecycler.NewsViewHolder> {

    private View view;
    private OnNewsClickListener onNewsClickListener;
    private List<EntityNews> newsList;

    public NewsRecycler(List<EntityNews> newsList, OnNewsClickListener onNewsClickListener) {
        this.newsList = newsList;
        this.onNewsClickListener = onNewsClickListener;
    }

    public void getAllNews(List<EntityNews> list){
        this.newsList = list;
    }

    public interface OnNewsClickListener{
        void onNewsClick(int position);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView newsTitle, newsAuthor, newsDate;
        OnNewsClickListener newsClickListener;

        public NewsViewHolder(@NonNull View itemView, OnNewsClickListener onClickListener) {
            super(itemView);

            newsClickListener = onClickListener;
            newsTitle = itemView.findViewById(R.id.tvNewsTitle);
            newsAuthor = itemView.findViewById(R.id.tvNewsAuthor);
            newsDate = itemView.findViewById(R.id.tvNewsDate);

            itemView.setOnClickListener(v ->{
                if (newsClickListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        newsClickListener.onNewsClick(position);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_rv,parent,false);
        return new NewsViewHolder(view, onNewsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        EntityNews newsResponse = newsList.get(position);

        holder.newsTitle.setText(newsResponse.getNews_title());
        holder.newsAuthor.setText(newsResponse.getNews_author());
        holder.newsDate.setText(newsResponse.getNews_date());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
