package com.app.nikhil.githubdemo.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.nikhil.githubdemo.Models.User;
import com.app.nikhil.githubdemo.NetworkUtils.ApiService;
import com.app.nikhil.githubdemo.NetworkUtils.ResponseCallbackInterface;
import com.app.nikhil.githubdemo.R;
import com.bumptech.glide.Glide;

public class Main2Activity extends AppCompatActivity {


    ApiService apiService;
    ImageView userDetailImageView;
    TextView userNameTv;
    TextView userBioTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        User user= (User) getIntent().getSerializableExtra("user");
        apiService=new ApiService();
        userNameTv=findViewById(R.id.userDetailNameTv);
        userBioTv=findViewById(R.id.userBioTv);
        userDetailImageView=findViewById(R.id.userDetailImageView);

        fetchUserDetails(user);

    }



    public void populateView(User user)
    {
        Glide.with(this).load(user.getAvatar_url()).into(userDetailImageView);
        userNameTv.setText(user.getName());
        userBioTv.setText(user.getBio());


    }


    public void fetchUserDetails(User user)
    {
        apiService.getUserDetails(user.getLogin(), new ResponseCallbackInterface<User>() {
            @Override
            public void success(User user) {

                populateView(user);


            }

            @Override
            public void failure(User user) {

            }
        });



    }




}
