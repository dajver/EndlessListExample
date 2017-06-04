package com.project.endlesslist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.endlesslist.R;
import com.project.endlesslist.api.model.GithubItemModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/4/17.
 */

public class ReposRecycleViewAdapter extends RecyclerView.Adapter<ReposRecycleViewAdapter.ViewHolder>{

    private List<GithubItemModel> realmModels = new ArrayList<>();
    private Context context;

    public ReposRecycleViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(GithubItemModel githubItemModel) {
        realmModels.add(githubItemModel);
    }

    @Override
    public ReposRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);
        ReposRecycleViewAdapter.ViewHolder pvh = new ReposRecycleViewAdapter.ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ReposRecycleViewAdapter.ViewHolder holder, final int position) {
        holder.name.setText(realmModels.get(position).getName());
        holder.description.setText(realmModels.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return realmModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.description)
        TextView description;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}