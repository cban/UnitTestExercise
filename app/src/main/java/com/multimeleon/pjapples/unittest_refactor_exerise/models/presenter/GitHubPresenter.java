package com.multimeleon.pjapples.unittest_refactor_exerise.models.presenter;

import android.support.annotation.NonNull;

import com.multimeleon.pjapples.unittest_refactor_exerise.models.model.SearchResponse;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.repository.GitHubRepository;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.view.GitHubContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubPresenter implements GitHubContract.Presenter {

    private final GitHubContract.View view;
    private final GitHubRepository gitHubRepository;


    public GitHubPresenter(GitHubRepository gitHubRepository, GitHubContract.View view) {
        this.view = view;
        this.gitHubRepository = gitHubRepository;
    }

    @Override
    public void searchGitHubRepo(String query) {
        Call<SearchResponse> call = this.gitHubRepository.searchGitHubRepository(query);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call,
                                   Response<SearchResponse> response) {
                GitHubPresenter.this.handleResponse(response);
            }

            @Override
            public void onFailure(Call<SearchResponse> call,
                                  Throwable t) {
                //Log.e("", "", t);
                GitHubPresenter.this.view.handleError("E103 - System error");
            }
        });
    }

    private void handleResponse(@NonNull Response<SearchResponse> response) {
        if (response.isSuccessful()) {
            SearchResponse searchResponse = response.body();
            if (searchResponse != null) {
                this.view.handleSearchResults(searchResponse.getSearchResults());
            } else {
                this.view.handleError("E102 - System error");
            }
        } else {
            this.view.handleError("E101 - System error");
        }
    }
}
