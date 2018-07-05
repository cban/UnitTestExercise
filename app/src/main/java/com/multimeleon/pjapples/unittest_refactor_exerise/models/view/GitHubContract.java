package com.multimeleon.pjapples.unittest_refactor_exerise.models.view;

import com.multimeleon.pjapples.unittest_refactor_exerise.models.model.SearchResult;

import java.util.List;

public interface GitHubContract {

    interface View  {

        void handleError(String message);
        void handleSearchResults(List<SearchResult> searchResults);


    }

    interface Presenter {
        void searchGitHubRepo(String query);
    }
}
