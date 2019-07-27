package com.gituser.api.gitrepo.controller;

import com.gituser.api.gitrepo.domain.*;
import com.gituser.api.gitrepo.service.RepositoryService;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(GitUserController.BASE_URL)
public class GitUserController {

    public static final String BASE_URL = "repo/api/v1/";

    private static final Logger log = LoggerFactory.getLogger(GitUserController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private final RepositoryService repositoryService;

    public GitUserController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }


    @GetMapping("/{userName}")
    public List<Repository> getRepositoryList(@PathVariable String userName, @RequestParam(value = "filter", required = false, defaultValue = "false") boolean filterOwnerRepos) {
        List<Repository> repositoryList = new ArrayList<>();
        List<GitHubUser> gitHubRepositoryList = getGitHubRepository(userName, filterOwnerRepos);
        for (GitHubUser gitHubUser : gitHubRepositoryList) {
            repositoryList.add(new Repository(gitHubUser.getRepositoryId(), "GitHub", gitHubUser.getRepositoryName()));
        }

        List<GitLabUser> gitLabUserList = getGitLabProject(userName, filterOwnerRepos);
        for (GitLabUser gitLabUser : gitLabUserList) {
            repositoryList.add(new Repository(gitLabUser.getId(), "GitLab", gitLabUser.getRepositoryName()));
        }

        return repositoryList;
    }

    public List<GitHubUser> getGitHubRepository(String userName, boolean filterOwnerRepos) {
        log.info("Fetch GitHub projects of user " + userName);
        String gitHubURL = environment.getProperty("GITHUB_BASE_URL") + userName + "/repos";
        HttpGet request = repositoryService.generateRequestUri(gitHubURL);
        List<GitHubRepository> userRepositoryList = repositoryService.getGitHubRepository(request);
        return repositoryService.getGitHubUserDetails(userRepositoryList, filterOwnerRepos, userName);
    }

    private List<GitLabUser> getGitLabProject(String userName, boolean filterOwnerRepos) {
        log.info("Fetch GitLab projects of user " + userName);
        String gitLabUserURL = environment.getProperty("GITLAB_BASE_URL") + "?username=" + userName;
        HttpGet request = repositoryService.generateRequestUri(gitLabUserURL);
        GitLabOwner gitLabOwner = repositoryService.getGitLabOwner(request);
        String gitLabProjectURL = environment.getProperty("GITLAB_BASE_URL") + "/" + gitLabOwner.getId() + "/projects";
        List<GitLabProject> gitLabProjectList = repositoryService.getGitLabProject(repositoryService.generateRequestUri(gitLabProjectURL));
        return repositoryService.getGitLabUserDetails(gitLabProjectList, filterOwnerRepos, gitLabOwner.getId());
    }
}
