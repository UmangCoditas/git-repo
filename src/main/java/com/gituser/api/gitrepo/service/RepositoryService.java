package com.gituser.api.gitrepo.service;

import com.gituser.api.gitrepo.domain.*;
import org.apache.http.client.methods.HttpGet;

import java.util.List;

public interface RepositoryService {

    HttpGet generateRequestUri(String endPointUri);

    List<GitHubRepository> getGitHubRepository(HttpGet request);

    GitLabOwner getGitLabOwner(HttpGet request);

    List<GitLabProject> getGitLabProject(HttpGet request);

    List<GitHubUser> getGitHubUserDetails(List<GitHubRepository> userRepositoryList, boolean filter, String userName);

    List<GitLabUser> getGitLabUserDetails(List<GitLabProject> gitLabProjectList, boolean filter, Integer userId);


}
