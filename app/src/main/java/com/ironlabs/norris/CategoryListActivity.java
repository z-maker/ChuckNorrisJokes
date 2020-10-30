package com.ironlabs.norris;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.ironlabs.CategoriesAdapter;
import com.ironlabs.chucknorrisapi.ApiConection;
import com.ironlabs.chucknorrisapi.Model.Joke;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListActivity extends AppCompatActivity  implements CategoriesAdapter.OnCategorySelected{

    private boolean mTwoPane;
    private ApiConection connection;
    private Context ctx;
    private List<String> categories;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        ctx = this;
        connection = new ApiConection();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }

        recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);
    }

    private void setupRecyclerView(@NonNull final RecyclerView recyclerView) {

        connection.getCategories(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                categories = response.body();
                binRecyclerData();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }

    private void binRecyclerData(){

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ctx,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        CategoriesAdapter adapter = new CategoriesAdapter(categories);
        adapter.setOnCategorySelected(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCategorySelected(String category) {

        final Intent intent = new Intent(this,JokeDetailActivity.class);
        intent.putExtra(JokeDetailFragment.JOKE_ITEM,category);
        startActivity(intent);

    }
}
