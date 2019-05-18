package com.app.nikhil.githubdemo.NetworkUtils;

import com.app.nikhil.githubdemo.Models.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApiClient {


    @GET("/users")
    Single<List<User>> getUsers();

    @GET("/users/{user_name}")
    Single<User> getUserDetails(@Path(value = "user_name", encoded = true) String userName);


}
