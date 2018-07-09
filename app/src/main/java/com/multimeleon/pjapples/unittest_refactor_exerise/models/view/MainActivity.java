package com.multimeleon.pjapples.unittest_refactor_exerise.models.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.multimeleon.pjapples.unittest_refactor_exerise.R;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.model.SearchResult;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.presenter.GitHubPresenter;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.repository.GitHubSearchRepositoryImp;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.service.GitHubApi;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.util.ApiUtils;

import java.util.List;


public class MainActivity extends AppCompatActivity implements GitHubContract.View {

    private ReposRvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Instantiate the Presenter
        GitHubApi gitHubApi = ApiUtils.getGitHubApiInterface();
        GitHubSearchRepositoryImp gitHubSearchRepositoryImp = new GitHubSearchRepositoryImp(gitHubApi);
        final GitHubPresenter mPresenter = new GitHubPresenter(gitHubSearchRepositoryImp, this);
        final EditText etSearchQuery = findViewById(R.id.et_search_query);

        etSearchQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v,
                                          int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mPresenter.searchGitHubRepo(etSearchQuery.getText().toString());
                    return true;
                }
                return false;
            }
        });

        RecyclerView rvRepos = findViewById(R.id.rv_repos);
        rvAdapter = new ReposRvAdapter();
        rvRepos.setHasFixedSize(true);
        rvRepos.setAdapter(rvAdapter);
    }

    @Override
    public void handleError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleSearchResults(List<SearchResult> searchResults) {
        rvAdapter.updateResults(searchResults);
    }
}