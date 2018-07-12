package com.android.omdbapp.omdb.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.omdbapp.omdb.R;
import com.android.omdbapp.omdb.adaptors.MovieDetailRecyAdaptor;
import com.android.omdbapp.omdb.data.Movie;
import com.android.omdbapp.omdb.data.MovieDetail;
import com.android.omdbapp.omdb.data.MovieResult;
import com.android.omdbapp.omdb.services.ApiHelper;
import com.android.omdbapp.omdb.services.ApiService;
import com.squareup.picasso.Picasso;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView recyview_moviedetails;
    private LinearLayoutManager linearLayoutManager;
    private MovieDetailRecyAdaptor movieDetailRecyAdaptor;
    private Movie movie;
    private ApiService apiService;
    private ImageView htab_header;
    private AppCompatTextView textview_movietitle,textview_rating,textview_movieyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        toolbar = findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        htab_header=findViewById(R.id.htab_header);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textview_movietitle=findViewById(R.id.textview_movietitle);
        textview_rating=findViewById(R.id.textview_rating);
        textview_movieyear=findViewById(R.id.textview_movieyear);
        recyview_moviedetails = findViewById(R.id.recyview_moviedetails);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyview_moviedetails.setLayoutManager(linearLayoutManager);
        movieDetailRecyAdaptor = new MovieDetailRecyAdaptor(this);
        recyview_moviedetails.setAdapter(movieDetailRecyAdaptor);
        movie = (Movie) getIntent().getSerializableExtra("data");
        apiService = ApiHelper.getRetrofitClient().create(ApiService.class);
        if (movie != null)
            movieDetails(movie);


    }





    private void movieDetails(final Movie movie) {
        {
            apiService.getMovieDetails(movie.getImdbID())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MovieDetail>() {

                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d("DEBUG", "onSubscribe: ");
                        }

                        @Override
                        public void onNext(MovieDetail movieResult) {
                            if (movieResult!=null) {
                                movieDetailRecyAdaptor.updateData(movieResult);
                                Picasso.get().load(movieResult.getPoster()).fit().into(htab_header);
                                textview_movietitle.setText(movieResult.getTitle());
                                textview_movieyear.setText(movieResult.getReleased());
                                textview_rating.setText(movieResult.getImdbRating()+"/10");

                            }
                            Log.d("DEBUG", "onNext: ");
                        }

                        @Override
                        public void onError(Throwable t) {
                            Toast.makeText(MovieDetailActivity.this, "Please try again!" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("DEBUG", "onError: " + t.getLocalizedMessage());
                        }

                        @Override
                        public void onComplete() {
                            Log.d("DEBUG", "onComplete: ");
                        }
                    });

        }
    }


}
