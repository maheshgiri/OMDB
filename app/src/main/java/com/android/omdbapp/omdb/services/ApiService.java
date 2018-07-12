package com.android.omdbapp.omdb.services;

import com.android.omdbapp.omdb.data.MovieDetail;
import com.android.omdbapp.omdb.data.MovieResult;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("/?apikey=8af44a12")
    Observable<MovieResult> search(@Query("s") String searchstring, @Query("page") int page);


    @GET("/?apikey=8af44a12")
    Observable<MovieDetail> getMovieDetails(@Query("i") String movieimdbid);



}
