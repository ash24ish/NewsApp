package com.ashishbharam.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.ashishbharam.newsapp.adapter.NewsRecycler;
import com.ashishbharam.newsapp.pojos.DefaultResponse;
import com.ashishbharam.newsapp.pojos.NewsResponse;
import com.ashishbharam.newsapp.retrofit.RetrofitClient;
import com.ashishbharam.newsapp.room.EntityNews;
import com.ashishbharam.newsapp.room.NewsRepository;
import com.ashishbharam.newsapp.room.NewsRoomDatabase;
import com.ashishbharam.newsapp.viewmodel.NewsViewModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsRecycler.OnNewsClickListener {
    private RecyclerView recyclerView, recyclerView1;
    private NewsRecycler adapter;
    private List<NewsResponse> newsResponseList = new ArrayList<>();
    private List<EntityNews> entityNewsList = new ArrayList<>();
    private static final String TAG = "MYLOG";
    private final String TAG2 = this.getClass().getSimpleName();
    private ContentLoadingProgressBar progressBar;
    private MaterialTextView materialTextView;

    private NewsViewModel newsViewModel;
    private NewsRepository newsRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("News Nation");
        recyclerView = findViewById(R.id.newsRecyclerView);
        recyclerView1 = findViewById(R.id.newsRecyclerView1);
        progressBar = findViewById(R.id.progressBar);
        materialTextView = findViewById(R.id.errorView);

        newsRepository = new NewsRepository(getApplication());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new NewsRecycler(entityNewsList, MainActivity.this);
        recyclerView.setAdapter(adapter);
        getNews();
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.getGetAllNewsList().observe(this, newsList -> {
            Log.d(TAG, "onChanged: WORKING");
            adapter.getAllNews(newsList);
        });
    }
// /data/data/com.ashishbharam.newsapp/databases/NewsDatabase

    private void getNews() {

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        Call<DefaultResponse> call = RetrofitClient.getInstance().getRetrofitApi().getResponse();

        call.enqueue(new Callback<DefaultResponse>() {

            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                assert response.body() != null;
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                materialTextView.setVisibility(View.GONE);
                entityNewsList = response.body().getResponse();
                //NewsRoomDatabase.getInstance(getApplicationContext()).newsDao().insert(entityNewsList);

                Log.d(TAG, "onResponse: " + entityNewsList.get(3).getNews_title());
                //newsRepository.insert(entityNewsList);
                newsViewModel.insert(entityNewsList);

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No Internet" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
                materialTextView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onNewsClick(int position) {
        Toast.makeText(MainActivity.this, "" + entityNewsList.get(position).getNews_id(), Toast.LENGTH_SHORT).show();
    }
}