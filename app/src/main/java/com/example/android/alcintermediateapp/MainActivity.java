package com.example.android.alcintermediateapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.alcintermediateapp.adapter.DataAdapter;
import com.example.android.alcintermediateapp.listner.RecyclerTouchListener;
import com.example.android.alcintermediateapp.model.User;
import com.example.android.alcintermediateapp.rest.GitHubJSONResponse;
import com.example.android.alcintermediateapp.rest.GitHubRequestInterface;
import com.example.android.alcintermediateapp.utilities.ApiUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DataAdapter adapter;
    GitHubRequestInterface mGitHubRequestInterface;
    private ArrayList<User> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mGitHubRequestInterface = ApiUtils.getJSON();
        adapter = new DataAdapter(this, new ArrayList<User>());
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
               Bundle bundle = new Bundle();
                User clickedUser = adapter.getUserByPos(position);
                bundle.putInt("position", position);
                Intent user_details = new Intent(MainActivity.this, UserDetails.class)
                        .putExtra("name",clickedUser.getLogin())
                        .putExtra("profileUrl",clickedUser.getHtmlUrl())
                        .putExtra("followingUrl",clickedUser.getFollowingUrl())
                        .putExtra("followersUrl",clickedUser.getFollowersUrl())
                        .putExtra("reposUrl",clickedUser.getReposUrl())
                        .putExtra("pictureUrl",clickedUser.getAvatarUrl());
                startActivity(user_details);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadAnswers();
    }
    public void loadAnswers(){
        mGitHubRequestInterface.getJSON().enqueue(new Callback<GitHubJSONResponse>() {
            @Override
            public void onResponse(Call<GitHubJSONResponse> call, Response<GitHubJSONResponse> response) {
                GitHubJSONResponse jsonResponse = response.body();
                if(response.isSuccessful()){

                    adapter.updateAnswers(response.body().getItems());

                }else{
                    int statusCode = response.code();
                }



            }


            @Override
            public void onFailure(Call<GitHubJSONResponse> call, Throwable t) {

            }
        });
    }
}
