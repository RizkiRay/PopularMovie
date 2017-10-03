package com.rizkiraynaldy.popularmovie.network;

import com.rizkiraynaldy.popularmovie.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ray on 14/06/2017.
 */

public interface ApiInterface {
    @GET("movie/popular")
    Call<MovieResponse> getMoviePopular(@Query("api_key") String apiKey);
    @GET("movie/top_rated")
    Call<MovieResponse> getMovieTopRated(@Query("api_key") String apiKey);
}
