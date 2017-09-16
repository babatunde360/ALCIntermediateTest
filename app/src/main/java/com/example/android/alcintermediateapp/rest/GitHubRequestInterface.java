package com.example.android.alcintermediateapp.rest;

import com.example.android.alcintermediateapp.model.User;

import retrofit2.Call;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tunde on 9/12/2017.
 */

public interface GitHubRequestInterface {
    @GET("search/users?q=location:lagos")
    Call<GitHubJSONResponse> getJSON1();

    @GET("search/users?q=type:user+location:Lagos+language:java&per_page=100")
    Call<GitHubJSONResponse> getJSON();

    @GET("/users/{username}")
    Call<User> getIndividual(@Path("username") String username);
}
