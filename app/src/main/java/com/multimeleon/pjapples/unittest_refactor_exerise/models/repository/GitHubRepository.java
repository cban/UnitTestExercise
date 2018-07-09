package com.multimeleon.pjapples.unittest_refactor_exerise.models.repository;

import com.multimeleon.pjapples.unittest_refactor_exerise.models.model.SearchResponse;

import retrofit2.Call;

public interface GitHubRepository {

    Call<SearchResponse> searchGitHubRepository(String query);
}
