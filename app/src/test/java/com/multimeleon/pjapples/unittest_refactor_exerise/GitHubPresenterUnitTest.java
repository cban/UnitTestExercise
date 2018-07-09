package com.multimeleon.pjapples.unittest_refactor_exerise;

import com.multimeleon.pjapples.unittest_refactor_exerise.models.model.SearchResponse;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.model.SearchResult;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.presenter.GitHubPresenter;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.repository.GitHubRepository;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.view.GitHubContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GitHubPresenterUnitTest {

    @Mock
    GitHubContract.View mockView;

    @Mock
    GitHubRepository gitHubRepository;


    GitHubContract.Presenter gitHubPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        gitHubPresenter = new GitHubPresenter(gitHubRepository, mockView);
    }

    @Test
    public void searchReturnResults() {
        // gitHubPresenter.handleResponse(searchResponse);
        //verify(mockView).handleError("E102 - System error");
    }

    @Test
    public void runTest() {

        final Call<SearchResponse> mockedCall = Mockito.mock(Call.class);

        when(gitHubRepository.searchGitHubRepository(anyString())).thenReturn(mockedCall);

        doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<SearchResponse> callback = invocation.getArgument(0);

                callback.onResponse(mockedCall, Response.success(new SearchResponse()));
                // or callback.onResponse(mockedCall, Response.error(404. ...);
                // or callback.onFailure(mockedCall, new IOException());

                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));

        gitHubPresenter.searchGitHubRepo(anyString());
        verify(gitHubRepository).searchGitHubRepository(anyString());
        verify(mockView).handleSearchResults(Mockito.<SearchResult>anyList());
    }


}
