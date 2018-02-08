package com.example.micha.searchgit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.micha.searchgit.model.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micha on 2/8/2018.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoHolder> {

    private List<Repository> list;

    public RepoAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public RepoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_layout, parent, false);
        return new RepoHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoHolder holder, int position) {
        holder.name.setText(list.get(position).getFullName());
        holder.repoUrl.setText(list.get(position).getHtmlUrl());
        holder.owner.setText(Integer.toString(list.get(position).getOwner().getId()));
        holder.stargazers.setText(Integer.toString(list.get(position).getStargazersCount()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RepoHolder extends RecyclerView.ViewHolder{

        private final TextView name,repoUrl,owner,stargazers;

        public RepoHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.repoName);
            repoUrl = itemView.findViewById(R.id.repoUrl);
            owner = itemView.findViewById(R.id.owner);
            stargazers = itemView.findViewById(R.id.stargazers);
        }
    }

    public void addToList(Repository[] repositories){
        for (int i = 0; i < repositories.length; i++) {
            list.add(repositories[i]);
        }
        notifyDataSetChanged();
    }

}
