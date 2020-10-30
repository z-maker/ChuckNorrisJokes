package com.ironlabs.norris;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ironlabs.chucknorrisapi.ApiConection;
import com.ironlabs.chucknorrisapi.Model.Joke;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JokeDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private String joke_category;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ctx = this;

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindJoke();
            }
        });


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        imageView = findViewById(R.id.joke_image);


        if (savedInstanceState == null) {
            joke_category = getIntent().getStringExtra(JokeDetailFragment.JOKE_ITEM);
            bindJoke();
        }
    }

    private void bindJoke(){

        getSupportActionBar().setTitle(joke_category);

        ApiConection conection = new ApiConection();
        conection.getCategoryJoke(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {

                JokeDetailFragment fragment = JokeDetailFragment.newInstance(response.body());

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment).disallowAddToBackStack()
                        .commit();


                Glide.with(ctx).load(response.body().getIconUrl()).into(imageView);

            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {

            }
        },joke_category);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            navigateUpTo(new Intent(this, CategoryListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
