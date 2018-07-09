package com.multimeleon.pjapples.unittest_refactor_exerise;

import com.multimeleon.pjapples.unittest_refactor_exerise.models.repository.GitHubRepository;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.repository.GitHubSearchRepositoryImp;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.service.GitHubApi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GitHubRepositoryUnitTest {

    @Mock
    GitHubApi mockGitHubApi;

    GitHubRepository gitHubRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        gitHubRepository = new GitHubSearchRepositoryImp(mockGitHubApi);
    }

    @Test
    void searchGitHubRepositoryMethodToSearchGetsCalled() {
        //gitHubRepository.searchGitHubRepository(anyString());

    }

}
