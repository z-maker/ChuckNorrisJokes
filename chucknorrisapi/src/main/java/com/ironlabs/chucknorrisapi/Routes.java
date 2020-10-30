package com.ironlabs.chucknorrisapi;

import com.ironlabs.chucknorrisapi.Model.Joke;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Routes {

    String RANDOM_JOKE = "random/";
    String JOKE_CATEGORIES = "categories/";
    String SEARCH_JOKES = "search/";


    @GET(RANDOM_JOKE)
    Call<Joke> getRandomJoke();

    @GET(JOKE_CATEGORIES)
    Call<List<String>> getCategories();

    @GET(RANDOM_JOKE)
    Call<Joke> getCategoryJoke(@Query("category") String category);

}
