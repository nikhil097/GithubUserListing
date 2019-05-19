package com.app.nikhil.githubdemo.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.nikhil.githubdemo.Adapter.RepoAdapter;
import com.app.nikhil.githubdemo.Models.Repo;
import com.app.nikhil.githubdemo.Models.User;
import com.app.nikhil.githubdemo.NetworkUtils.ApiService;
import com.app.nikhil.githubdemo.NetworkUtils.ResponseCallbackInterface;
import com.app.nikhil.githubdemo.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsActivity extends AppCompatActivity {


    ApiService apiService;
    ImageView userDetailImageView;
    TextView userNameTv,followingTv,followersTv,locationTv;
    TextView userBioTv;
    RecyclerView reposRv;
    ProgressBar detailsProgressLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        User user= (User) getIntent().getSerializableExtra("user");
        apiService=new ApiService();
        userNameTv=findViewById(R.id.userDetailNameTv);
        userBioTv=findViewById(R.id.userBioTv);
        userDetailImageView=findViewById(R.id.userDetailImageView);
        followersTv=findViewById(R.id.followersTv);
        followingTv=findViewById(R.id.followingTv);
        locationTv=findViewById(R.id.userDetailLocationTv);
        reposRv=findViewById(R.id.userReposRv);
        detailsProgressLoadingBar =findViewById(R.id.detailsLoadingProgressBar);

        fetchUserDetails(user);

    }



    public void populateView(User user)
    {
        Glide.with(this).load(user.getAvatar_url()).into(userDetailImageView);
        userNameTv.setText(user.getName());

        if(user.getBio()!=null) {
            userBioTv.setText(user.getBio());
        }

        if (user.getLocation()!=null)
        locationTv.setText(user.getLocation());

        followingTv.setText("Following: "+user.getFollowing());
        followersTv.setText("Followers: "+user.getFollowers());

    }


    public void fetchUserDetails(User user)
    {
        apiService.getUserDetails(user.getLogin(), new ResponseCallbackInterface<User>() {
            @Override
            public void success(User user) {

                populateView(user);

                fetchRepos(user);

            }

            @Override
            public void failure(User user) {

                detailsProgressLoadingBar.setVisibility(View.GONE);
                Toast.makeText(UserDetailsActivity.this, ""+user.getErrorMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }



    public void populateRepoRecyclerView(ArrayList<Repo> reposList)
    {

        RepoAdapter repoAdapter=new RepoAdapter(UserDetailsActivity.this,reposList);

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(UserDetailsActivity.this);
        reposRv.setLayoutManager(mLayoutManager);
        reposRv.setItemAnimator(new DefaultItemAnimator());
        reposRv.setAdapter(repoAdapter);

    }



    public void fetchRepos(User user)
    {
        apiService.getRepos(user.getLogin(), new ResponseCallbackInterface<List<Repo>>() {
            @Override
            public void success(List<Repo> repos) {

                populateRepoRecyclerView((ArrayList<Repo>) repos);
                detailsProgressLoadingBar.setVisibility(View.GONE);
            }

            @Override
            public void failure(List<Repo> repos) {
                detailsProgressLoadingBar.setVisibility(View.GONE);
                Toast.makeText(UserDetailsActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });


    }




}
