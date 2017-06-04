package com.project.endlesslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.project.endlesslist.adapter.ReposRecycleViewAdapter;
import com.project.endlesslist.api.RestClient;
import com.project.endlesslist.api.model.GithubItemModel;
import com.project.endlesslist.api.model.GithubModel;
import com.project.endlesslist.view.EndlessRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements EndlessRecyclerView.OnLoadMoreListener,
        Callback<GithubModel> {

    private static final int MAX_ITEMS_PER_PAGE = 100;

    @BindView(R.id.recycleView)
    EndlessRecyclerView recyclerView;
    @BindView(R.id.editText)
    EditText editText;

    ReposRecycleViewAdapter reposRecycleViewAdapter;
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        reposRecycleViewAdapter = new ReposRecycleViewAdapter(this);
        recyclerView.setOnLoadMoreListener(this);
        recyclerView.setAdapter(reposRecycleViewAdapter);
    }

    @OnClick(R.id.button)
    public void onSearchClick() {
        RestClient. instance().getSearchedRepos(editText.getText().toString(), currentPage, MAX_ITEMS_PER_PAGE).enqueue(this);
    }

    @Override
    public void onLoadMore() {
        currentPage++;
        RestClient. instance().getSearchedRepos(editText.getText().toString(), currentPage, MAX_ITEMS_PER_PAGE).enqueue(this);
    }

    @Override
    public void onResponse(Call<GithubModel> call, Response<GithubModel> response) {
        GithubModel githubModel = response.body() != null ? response.body() : new GithubModel();
        for(GithubItemModel model : githubModel.getItems())
            reposRecycleViewAdapter.addItem(model);
        reposRecycleViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<GithubModel> call, Throwable t) {
        t.printStackTrace();
    }
}
