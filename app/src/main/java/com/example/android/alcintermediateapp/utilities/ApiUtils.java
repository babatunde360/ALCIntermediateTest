package com.example.android.alcintermediateapp.utilities;

import com.example.android.alcintermediateapp.rest.GitHubJSONResponse;
import com.example.android.alcintermediateapp.rest.GitHubRequestInterface;
import com.example.android.alcintermediateapp.rest.RetrofitClient;

/**
 * Created by tunde on 9/13/2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://api.github.com";

    public static GitHubRequestInterface getJSON() {
        return RetrofitClient.getClient(BASE_URL).create(GitHubRequestInterface.class);
    }
}
