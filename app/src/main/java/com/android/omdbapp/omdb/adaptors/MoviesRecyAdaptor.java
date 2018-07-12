package com.android.omdbapp.omdb.adaptors;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.omdbapp.omdb.ui.MovieDetailActivity;
import com.android.omdbapp.omdb.R;
import com.android.omdbapp.omdb.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesRecyAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context appContext;
    private LayoutInflater layoutInflater;
    private List<Movie> movieList=new ArrayList<>();
    public void updateList(List<Movie> movies){
        if (movies!=null) {
            movieList.addAll(movies);
            notifyDataSetChanged();
        }
    }

    public MoviesRecyAdaptor(Context appcontext){
        movieList.clear();
        this.appContext=appcontext;
        layoutInflater=LayoutInflater.from(appContext);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=layoutInflater.inflate(R.layout.row_movies,parent,false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder movieViewHolder= (MovieViewHolder) holder;
        final Movie movie=movieList.get(position);
        Picasso.get().load(movie.getPoster()).fit().into(movieViewHolder.imageview_movie);
        movieViewHolder.textview_movietitle.setText(""+movie.getTitle());
        movieViewHolder.textview_movieyear.setText(""+movie.getYear());

        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent moviedetails=new Intent(appContext, MovieDetailActivity.class);
              moviedetails.putExtra("data",movie);
                appContext.startActivity(moviedetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    protected static class MovieViewHolder extends RecyclerView.ViewHolder{

        private AppCompatTextView textview_movietitle,textview_movieyear;
        private ImageView imageview_movie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imageview_movie=itemView.findViewById(R.id.imageview_movie);
            textview_movietitle=itemView.findViewById(R.id.textview_movietitle);
            textview_movieyear=itemView.findViewById(R.id.textview_movieyear);
        }

        public AppCompatTextView getTextview_movietitle() {
            return textview_movietitle;
        }

        public AppCompatTextView getTextview_movieyear() {
            return textview_movieyear;
        }

        public ImageView getImageview_movie() {
            return imageview_movie;
        }
    }


}
