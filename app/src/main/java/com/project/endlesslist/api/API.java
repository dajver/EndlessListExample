package com.project.endlesslist.api;

import com.project.endlesslist.api.model.GithubModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gleb on 6/4/17.
 */

public interface API {

    @GET("search/repositories")
    Call<GithubModel> getSearchedRepos(@Query("q") String q,
                                       @Query("page") int page,
                                        @Query("per_page") int perPage);
}
