package com.app.nikhil.githubdemo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.nikhil.githubdemo.Models.Repo;
import com.app.nikhil.githubdemo.R;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder>{


    Context context;
    ArrayList<Repo> reposList;

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repo_item,viewGroup,false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder repoViewHolder, int i) {

        Repo repo=reposList.get(i);

        repoViewHolder.repoTv.setText(repo.getName());

    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

    public RepoAdapter(Context context, ArrayList<Repo> reposList) {
        this.context = context;
        this.reposList = reposList;
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder{

        TextView repoTv;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            repoTv=itemView.findViewById(R.id.repoTv);
        }
    }


}
