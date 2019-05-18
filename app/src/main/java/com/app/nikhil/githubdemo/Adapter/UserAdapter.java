package com.app.nikhil.githubdemo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.nikhil.githubdemo.Models.User;
import com.app.nikhil.githubdemo.R;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    ArrayList<User> usersList;
    Context context;

    public UserAdapter(ArrayList<User> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item_layout,viewGroup,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {

        User user=usersList.get(i);

        userViewHolder.userNameTv.setText(user.getLogin());
        userViewHolder.userUrlTv.setText(user.getHtml_url());

        Glide.with(context).load(user.getAvatar_url()).into(userViewHolder.userImageView);

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        ImageView userImageView;
        TextView userNameTv;
        TextView userUrlTv;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTv=itemView.findViewById(R.id.userNameTv);
            userImageView=itemView.findViewById(R.id.userImageView);
            userUrlTv=itemView.findViewById(R.id.userUrlTv);
        }
    }


}
