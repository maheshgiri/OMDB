package com.android.omdbapp.omdb.ui;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.omdbapp.omdb.R;
import com.android.omdbapp.omdb.data.MovieResult;
import com.android.omdbapp.omdb.services.ApiHelper;
import com.android.omdbapp.omdb.services.ApiService;
import com.android.omdbapp.omdb.utils.FontCache;


import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private AppCompatEditText inputedittext_searchmovie;
    private TextView textview_title;
    private TextInputLayout inputlayout_searchmovie;
    private Button button_searchmovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputedittext_searchmovie=findViewById(R.id.inputedittext_searchmovie);
        textview_title=findViewById(R.id.textview_title);
        button_searchmovies=findViewById(R.id.button_searchmovies);
        inputlayout_searchmovie=findViewById(R.id.inputlayout_searchmovie);
        button_searchmovies.setTypeface(FontCache.getTypeface(this,"fonts/Montserrat-Regular.ttf"));
        inputlayout_searchmovie.setTypeface(FontCache.getTypeface(this,"fonts/Montserrat-Regular.ttf"));
        if (savedInstanceState!=null)
            inputedittext_searchmovie.setText(savedInstanceState.getString("inputmovietext"));


    }


    private void searchMovies(String searchstr){
        Intent movieintent=new Intent(this,MovieActivity.class);
         movieintent.putExtra("searchstring",searchstr);
        startActivity(movieintent);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("inputmovietext",inputedittext_searchmovie.getText().toString());
        super.onSaveInstanceState(outState);
    }

    public void searchMovies(View view) {
        searchMovies(inputedittext_searchmovie.getText().toString());

    }


}
