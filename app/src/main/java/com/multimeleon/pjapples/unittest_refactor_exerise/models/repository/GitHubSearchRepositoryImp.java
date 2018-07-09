package com.multimeleon.pjapples.unittest_refactor_exerise.models.repository;

import com.multimeleon.pjapples.unittest_refactor_exerise.models.model.SearchResponse;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.service.GitHubApi;

import retrofit2.Call;

public class GitHubSearchRepositoryImp implements GitHubRepository {


    private GitHubApi gitHubApi;

    public GitHubSearchRepositoryImp(GitHubApi gitHubApi) {
        this.gitHubApi = gitHubApi;
    }

    @Override
    public Call<SearchResponse> searchGitHubRepository(String query) {
        return gitHubApi.searchRepos(query);

    }

}
