package com.app.nikhil.githubdemo.NetworkUtils;

import android.annotation.SuppressLint;

import com.app.nikhil.githubdemo.Models.Repo;
import com.app.nikhil.githubdemo.Models.User;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {


    GithubApiClient githubApiClient;

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(NetworkConfig.base_url)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public ApiService()
    {
        githubApiClient=ApiService.getClient().create(GithubApiClient.class);
    }


    @SuppressLint("CheckResult")
    public void getUsers(String perPageItems,final ResponseCallbackInterface<List<User>> callbackInterface)
    {
        githubApiClient.getUsers(perPageItems).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<User> users) {

                        callbackInterface.success(users);

                    }

                    @Override
                    public void onError(Throwable e) {

                        callbackInterface.failure(null);

                    }
                });

    }


    @SuppressLint("CheckResult")
    public void getRepos(String userName, final ResponseCallbackInterface<List<Repo>> callbackInterface)
    {
        githubApiClient.getRepos(userName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Repo> repos) {

                        callbackInterface.success(repos);

                    }

                    @Override
                    public void onError(Throwable e) {

                        callbackInterface.failure(null);

                    }
                });
    }




    @SuppressLint("CheckResult")
    public void getUserDetails(String userName,final ResponseCallbackInterface<User> callbackInterface)
    {
        githubApiClient.getUserDetails(userName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(User user) {

                        callbackInterface.success(user);

                    }

                    @Override
                    public void onError(Throwable e) {

                        User user=new User();
                        user.setErrorMessage(e.getMessage());
                        callbackInterface.failure(user);

                    }
                });


    }






}
