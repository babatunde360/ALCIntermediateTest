package com.example.android.alcintermediateapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.ConnectionService;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.alcintermediateapp.model.User;
import com.example.android.alcintermediateapp.rest.GitHubJSONResponse;
import com.example.android.alcintermediateapp.rest.GitHubRequestInterface;
import com.example.android.alcintermediateapp.rest.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.data;
import static android.R.attr.name;

import static java.security.AccessController.getContext;

public class UserDetails extends AppCompatActivity {

    private TextView fullname;

    private String profileLink;
    private String pictureLink;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        final Intent intent = getIntent();
        username = getIntent().getExtras().getString("name");
        profileLink = intent.getExtras().getString("profileUrl");
         pictureLink = intent.getExtras().getString("pictureUrl");


        if (!((Activity) UserDetails.this).isFinishing()) {
            GitHubRequestInterface service = RetrofitClient.retrofit.create(GitHubRequestInterface.class);
            service.getIndividual(username)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            populateSubView(response.body());

                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.d("LOG_TAG", t.toString());
                        }
                    });

            ImageView userImage = (ImageView)findViewById(R.id.f_image);
            Picasso.with(getApplicationContext())
                    .load(pictureLink)
                    .into(userImage);

            TextView profileUrl = (TextView)findViewById(R.id.f_url);
            profileUrl.setText(profileLink);

            profileUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(profileLink);
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            });

            Button shareBtn = (Button) findViewById(R.id.share_btn);
            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StringBuilder intentText = new StringBuilder();
                    intentText.append("Check out this awesome developer @");
                    intentText.append(username);
                    intentText.append(", ");
                    intentText.append(profileLink);

                    Intent shareProfile = new Intent();
                    shareProfile.setAction(Intent.ACTION_SEND);
                    shareProfile.putExtra(Intent.EXTRA_TEXT, intentText.toString());
                    shareProfile.setType("text/plain");
                    startActivity(Intent.createChooser(shareProfile, "Share info"));
                }
            });

            fullname = (TextView) findViewById(R.id.full_name);




        }

    }

    private void inflateView(String username) {

        if (!((Activity) UserDetails.this).isFinishing()) {
            Toast.makeText(getApplicationContext(), username, Toast.LENGTH_SHORT).show();

/*            TextView userNames = (TextView)findViewById(R.id.f_name);
            userNames.setText("@" + name);*/


        }
    }



    private void populateSubView(User user){
        fullname.setText(user.getLogin());

    }
}
