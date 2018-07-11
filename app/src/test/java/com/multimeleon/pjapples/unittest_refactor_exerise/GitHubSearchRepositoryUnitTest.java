package com.multimeleon.pjapples.unittest_refactor_exerise;

import com.multimeleon.pjapples.unittest_refactor_exerise.models.model.SearchResponse;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.repository.GitHubRepository;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.repository.GitHubSearchRepositoryImp;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.service.GitHubApi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import retrofit2.Call;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GitHubSearchRepositoryUnitTest {

    @Mock
    GitHubApi mockGitHubApi;

    GitHubRepository gitHubRepository;

    @Mock
    private Call<SearchResponse> response;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        gitHubRepository = new GitHubSearchRepositoryImp(mockGitHubApi);
        when(this.gitHubRepository.searchGitHubRepository(anyString())).thenReturn(response);

    }

    @Test
    public void searchGitHubRepositoryMethodToSearchGetsCalled() {
        this.gitHubRepository.searchGitHubRepository(anyString());
        verify(mockGitHubApi).searchRepos(anyString());
    }

}
