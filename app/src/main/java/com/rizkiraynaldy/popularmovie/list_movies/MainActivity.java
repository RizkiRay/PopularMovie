package com.rizkiraynaldy.popularmovie.list_movies;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.rizkiraynaldy.popularmovie.R;
import com.rizkiraynaldy.popularmovie.SpacesItemDecoration;
import com.rizkiraynaldy.popularmovie.detail_movie.MovieDetailActivity;
import com.rizkiraynaldy.popularmovie.model.Movie;
import com.rizkiraynaldy.popularmovie.model.MovieResponse;
import com.rizkiraynaldy.popularmovie.network.ApiClient;
import com.rizkiraynaldy.popularmovie.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ListItemClickListener,
        AdapterView.OnItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_movie);
        AppCompatSpinner mSpinnerSort = (AppCompatSpinner) findViewById(R.id.spinner_sort);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        int spacingPixels = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingPixels));

        mSpinnerSort.setOnItemSelectedListener(this);

        getPopularMovie();
    }

    private void getPopularMovie() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = service.getMoviePopular(ApiClient.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse apiResponse = response.body();
                movies = apiResponse.getMoviePopulars();
                MovieAdapter adapter = new MovieAdapter(movies, MainActivity.this);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void getTopRatedMovie() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = service.getMovieTopRated(ApiClient.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse apiResponse = response.body();
                movies = apiResponse.getMoviePopulars();
                MovieAdapter adapter = new MovieAdapter(movies, MainActivity.this);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
    }

    //on poster click
    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie",movies.get(clickedItemIndex));
        startActivity(intent);
    }

    //on spinner item selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemSelected: " + position);
        if (position == 0) {
            getPopularMovie();
        } else {
            getTopRatedMovie();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
