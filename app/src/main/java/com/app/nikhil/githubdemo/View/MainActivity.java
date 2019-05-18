package com.app.nikhil.githubdemo.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.app.nikhil.githubdemo.Adapter.UserAdapter;
import com.app.nikhil.githubdemo.Models.User;
import com.app.nikhil.githubdemo.NetworkUtils.ApiService;
import com.app.nikhil.githubdemo.NetworkUtils.ResponseCallbackInterface;
import com.app.nikhil.githubdemo.R;
import com.app.nikhil.githubdemo.RecyclerViewTouchListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ApiService apiService;
    RecyclerView usersRv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService=new ApiService();

        usersRv=findViewById(R.id.usersRv);

        fetchUsers();

    }



    public void fetchUsers()
    {
        apiService.getUsers(new ResponseCallbackInterface<List<User>>() {
            @Override
            public void success(List<User> users) {

                populateRecyclerView((ArrayList<User>) users);

            }

            @Override
            public void failure(List<User> users) {

            }
        });

    }


    public void populateRecyclerView(final ArrayList<User> usersList)
    {
        UserAdapter userAdapter=new UserAdapter(usersList,MainActivity.this);

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        usersRv.setLayoutManager(mLayoutManager);
        usersRv.setItemAnimator(new DefaultItemAnimator());
        usersRv.setAdapter(userAdapter);

        usersRv.addOnItemTouchListener(new RecyclerViewTouchListener(MainActivity.this, usersRv, new RecyclerViewTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                User user=usersList.get(position);
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("user",user);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


    




}
