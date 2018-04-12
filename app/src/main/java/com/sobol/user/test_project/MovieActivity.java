package com.sobol.user.test_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

       Movie movie = (Movie) getIntent().getSerializableExtra("Movie");
        getSupportActionBar().setTitle(movie.title);
    }
}
