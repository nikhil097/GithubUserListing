package com.app.nikhil.githubdemo.NetworkUtils;

public interface ResponseCallbackInterface<T> {

    void success(T t);

    void  failure(T t);


}
