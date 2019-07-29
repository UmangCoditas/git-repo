package com.gituser.api.gitrepo.domain;

import lombok.Data;


@Data
public class GitHubUser {
    private String repositoryName;
    private boolean publicRepository;
    private String loginId;
    private Integer repositoryId;
    private boolean forked;

    public GitHubUser(String repositoryName, String loginId, boolean publicRepository,Integer repositoryId,boolean forked) {
        this.repositoryName = repositoryName;
        this.loginId = loginId;
        this.publicRepository = publicRepository;
        this.repositoryId = repositoryId;
        this.forked = forked;
    }
}
