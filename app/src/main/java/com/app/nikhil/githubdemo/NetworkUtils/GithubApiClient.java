package com.app.nikhil.githubdemo.NetworkUtils;

import com.app.nikhil.githubdemo.Models.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface GithubApiClient {


    @GET("/users")
    Single<List<User>> getUsers();
    


}
