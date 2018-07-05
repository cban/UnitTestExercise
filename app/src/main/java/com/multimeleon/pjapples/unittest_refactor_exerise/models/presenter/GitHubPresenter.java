package com.multimeleon.pjapples.unittest_refactor_exerise.models.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.multimeleon.pjapples.unittest_refactor_exerise.models.model.SearchResponse;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.service.GitHubApi;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.util.ApiUtils;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.view.GitHubContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubPresenter implements GitHubContract.Presenter {

    private final GitHubContract.View view;
    GitHubApi gitHubApi = ApiUtils.getGitHubApiInterface();

    public GitHubPresenter(GitHubContract.View view) {
        this.view = view;
    }

    @Override
    public void searchGitHubRepo(String query) {
        Call<SearchResponse> call = gitHubApi.searchRepos(query);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call,
                                   Response<SearchResponse> response) {
                handleResponse(response);
            }

            @Override
            public void onFailure(Call<SearchResponse> call,
                                  Throwable t) {
                Log.e("", "", t);
                view.handleError("E103 - System error");
            }
        });
    }

    private void handleResponse(@NonNull Response<SearchResponse> response) {
        if (response.isSuccessful()) {
            SearchResponse searchResponse = response.body();
            if (searchResponse != null) {
                view.handleSearchResults(searchResponse.getSearchResults());
            } else {
                view.handleError("E102 - System error");
            }
        } else {
            view.handleError("E101 - System error");
        }
    }
}
