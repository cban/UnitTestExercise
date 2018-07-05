package com.multimeleon.pjapples.unittest_refactor_exerise.models.util;

import com.multimeleon.pjapples.unittest_refactor_exerise.models.service.GitHubApi;
import com.multimeleon.pjapples.unittest_refactor_exerise.models.service.GitHubService;

public class ApiUtils {
    public static final String  BASE_URL = "https://api.github.com";

    public static GitHubApi getGitHubApiInterface() {
        return GitHubService.getClient(BASE_URL).create(GitHubApi.class);
    }
}
