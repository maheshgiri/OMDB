package com.android.omdbapp.omdb.adaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.omdbapp.omdb.R;
import com.android.omdbapp.omdb.data.MovieDetail;

public class MovieDetailRecyAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context appContext;

    private LayoutInflater layoutInflater;
    private MovieDetail movieDetail;

    public MovieDetailRecyAdaptor(Context context) {
        appContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void updateData(MovieDetail movieDetail) {
        if (movieDetail!=null) {
            this.movieDetail = movieDetail;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_moviedetail, parent, false);

        return new MovieDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MovieDetailViewHolder movieDetailViewHolder= (MovieDetailViewHolder) holder;

        switch (position) {
            case 0://director
                movieDetailViewHolder.textview_title.setText("Director");
                movieDetailViewHolder.textview_desc.setText(""+movieDetail.getDirector());
                break;
            case 1://released date

                movieDetailViewHolder.textview_title.setText("Released date");
                movieDetailViewHolder.textview_desc.setText(""+movieDetail.getReleased());
                break;
            case 2://storyline
                movieDetailViewHolder.textview_title.setText("Storyline");
                movieDetailViewHolder.textview_desc.setText(movieDetail.getPlot());
                break;
            case 3:
                break;
            case 4:
                break;

            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;


        }


    }

    @Override
    public int getItemCount() {
        if(movieDetail!=null)
        return 3;
        else return 0;
    }

    protected static class MovieDetailViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView textview_title, textview_desc;
        private ImageView imageview_type;

        public MovieDetailViewHolder(View itemView) {
            super(itemView);
            textview_title = itemView.findViewById(R.id.textview_title);
            textview_desc = itemView.findViewById(R.id.textview_desc);
            imageview_type = itemView.findViewById(R.id.imageview_type);
        }

    }

}
