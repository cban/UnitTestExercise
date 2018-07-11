package com.multimeleon.pjapples.unittest_refactor_exerise;

import com.multimeleon.pjapples.unittest_refactor_exerise.models.model.SearchResponse;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.presenter.GitHubPresenter;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.repository.GitHubRepository;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.view.GitHubContract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.List;

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
    private GitHubContract.View mockView;

    @Mock
    private GitHubRepository gitHubRepository;

    @Mock
    private Call<SearchResponse> searchResponseCall;

    private GitHubContract.Presenter gitHubPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        gitHubPresenter = new GitHubPresenter(this.gitHubRepository, this.mockView);
        when(gitHubRepository.searchGitHubRepository(anyString())).thenReturn(searchResponseCall);

    }

    @Test
    public void searchGitHubRepositoryMethodToReturnsACall() {
        Call<SearchResponse> myResponse = gitHubRepository.searchGitHubRepository("test");
        Assert.assertEquals(myResponse, searchResponseCall);
    }

    @Test
    public void searchGitHubRepositoryMethodToSearchGetsCalled() {
        gitHubPresenter.searchGitHubRepo(anyString());
        verify(this.gitHubRepository).searchGitHubRepository(anyString());
    }

    @Test
    public void HandleSearchResultsMethodIsCalled() {

        final Call<SearchResponse> mockedCall = Mockito.mock(Call.class);
        when(gitHubRepository.searchGitHubRepository(anyString())).thenReturn(mockedCall);

        doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<SearchResponse> callback = invocation.getArgument(0);
                callback.onResponse(mockedCall, Response.success(new SearchResponse()));


                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));

        gitHubPresenter.searchGitHubRepo(anyString());
        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(mockView).handleSearchResults(captor.capture());

    }

    @Test
    public void handleResponseWhenResponseIsNullMethodIsCalled() {

        final Call<SearchResponse> mockedCall = Mockito.mock(Call.class);

        when(gitHubRepository.searchGitHubRepository(anyString())).thenReturn(mockedCall);

        doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<SearchResponse> callback = invocation.getArgument(0);
                Response<SearchResponse> searchResponseResponse = Response.success(null);
                callback.onResponse(mockedCall, searchResponseResponse);
                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));

        gitHubPresenter.searchGitHubRepo(anyString());
        verify(this.mockView).handleError("E102 - System error");
    }


    @Test
    public void shouldCallMethodForDisplayingErrorMessageOnFailure() {

        final Call<SearchResponse> mockedCall = Mockito.mock(Call.class);
        when(gitHubRepository.searchGitHubRepository(Mockito.anyString())).thenReturn(mockedCall);

        doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<SearchResponse> callback = invocation.getArgument(0);
                callback.onFailure(mockedCall, new IOException());

                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));

        gitHubPresenter.searchGitHubRepo(anyString());
        verify(this.gitHubRepository).searchGitHubRepository(anyString());
        verify(this.mockView).handleError("E103 - System error");
    }

}
