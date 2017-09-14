package com.example.android.alcintermediateapp.rest;

import retrofit2.Call;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tunde on 9/12/2017.
 */

public interface GitHubRequestInterface {
    @GET("search/users?q=location:lagos")
    Call<GitHubJSONResponse> getJSON();

    @GET("search/users?q=type:user+location:Lagos+language:java&per_page=100")
    Call<GitHubJSONResponse> getJSONs();
}
