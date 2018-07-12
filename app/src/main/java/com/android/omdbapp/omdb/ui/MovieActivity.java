package com.android.omdbapp.omdb.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.omdbapp.omdb.R;
import com.android.omdbapp.omdb.adaptors.MoviesRecyAdaptor;
import com.android.omdbapp.omdb.data.MovieResult;
import com.android.omdbapp.omdb.services.ApiHelper;
import com.android.omdbapp.omdb.services.ApiService;
import com.android.omdbapp.omdb.utils.EndlessScrollListener;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieActivity extends AppCompatActivity  {


    private RecyclerView recyviewmovies;
    private LinearLayoutManager linearLayoutManager;
    private MoviesRecyAdaptor moviesRecyAdaptor;
    private Toolbar toolbar;
    private EndlessScrollListener endlessScrollListener;
    private Disposable searchresults;
    private ApiService apiService;
    private String searchStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyviewmovies = findViewById(R.id.recyviewmovies);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                linearLayoutManager.getOrientation());
        recyviewmovies.setLayoutManager(linearLayoutManager);
        recyviewmovies.addItemDecoration(dividerItemDecoration);
        moviesRecyAdaptor = new MoviesRecyAdaptor(this);
        recyviewmovies.setAdapter(moviesRecyAdaptor);
        apiService= ApiHelper.getRetrofitClient().create(ApiService.class);

        endlessScrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(page);
            }
        };
        recyviewmovies.addOnScrollListener(endlessScrollListener);
        //
        searchStr=getIntent().getStringExtra("searchstring");
        getSupportActionBar().setTitle(""+searchStr);
        searchMovies(1);
    }

    private void searchMovies(int pageno){

        apiService.search(searchStr,pageno)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResult>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        searchresults=d;
                        Log.d("DEBUG", "onSubscribe: ");
                    }

                    @Override
                    public void onNext(MovieResult movieResult) {
                        if (movieResult!=null)
                        moviesRecyAdaptor.updateList(movieResult.getSearch());
                        Log.d("DEBUG", "onNext: ");
                    }

                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(MovieActivity.this, "Please try again!"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("DEBUG", "onError: "+t.getLocalizedMessage() );
                    }

                    @Override
                    public void onComplete() {
                        Log.d("DEBUG", "onComplete: ");
                    }
                });

    }

    public void loadNextDataFromApi(int offset) {
        searchMovies(offset);
    }


}
