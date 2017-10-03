package com.rizkiraynaldy.popularmovie.detail_movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.rizkiraynaldy.popularmovie.R;
import com.rizkiraynaldy.popularmovie.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Movie movie = getIntent().getExtras().getParcelable("movie");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ImageView imagePoster = (ImageView) findViewById(R.id.image_poster);
        TextView textTitle = (TextView) findViewById(R.id.text_title);
        TextView textReleaseDate = (TextView) findViewById(R.id.text_release_date);
        TextView textSynopsis = (TextView) findViewById(R.id.text_synopsis);
        TextView textRate = (TextView) findViewById(R.id.text_rate);

        Picasso.with(this).load(Movie.IMAGE_URL + movie.moviePoster).fit().centerInside().into(imagePoster);
        textTitle.setText(movie.originalTitle);
        textReleaseDate.setText(movie.releaseDate);
        textSynopsis.setText(movie.plotSynopsis);
        textRate.setText(movie.userRating);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
