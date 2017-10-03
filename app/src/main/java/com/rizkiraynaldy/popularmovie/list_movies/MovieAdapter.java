package com.rizkiraynaldy.popularmovie.list_movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rizkiraynaldy.popularmovie.R;
import com.rizkiraynaldy.popularmovie.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ray on 14/06/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();
    private List<Movie> movies;
    private Context context;
    private ListItemClickListener mOnClickListener;

    public MovieAdapter(List<Movie> movies, ListItemClickListener listener) {
        this.movies = movies;
        mOnClickListener = listener;
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Log.i(TAG, "onBindViewHolder: Title " + movie.originalTitle);
        Log.i(TAG, "onBindViewHolder: Poster " + movie.IMAGE_URL + movie.moviePoster);
        Picasso.with(context).load(movie.IMAGE_URL + movie.moviePoster).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.imagePoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imagePoster;
        public ViewHolder(View itemView) {
            super(itemView);
            imagePoster = (ImageView) itemView.findViewById(R.id.image_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
