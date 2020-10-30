package com.ironlabs.chucknorrisapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ironlabs.chucknorrisapi.Model.Joke;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConection {

    private static String BASE_URL = "https://api.chucknorris.io/jokes/";

    static Retrofit retrofit;
    static Routes routes;

    public ApiConection() {
        retrofit = getRetrofitClient();
        routes = retrofit.create(Routes.class);
    }

    private static Retrofit getRetrofitClient() {

        if (retrofit == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


    public void getRandomJoke(Callback<Joke> callback){
        Call<Joke> call = routes.getRandomJoke();
        call.enqueue(callback);
    }

    public void getCategories(Callback<List<String>> callback){
        Call<List<String>> call = routes.getCategories();
        call.enqueue(callback);
    }


    public void getCategoryJoke(Callback<Joke> callback, String category){

        Call<Joke> call = routes.getCategoryJoke(category);
        call.enqueue(callback);

    }

    public void getCategoryJokes(Callback callback, String category){

        Call call = routes.getCategoryJoke(category);
        call.enqueue(callback);

    }



}
