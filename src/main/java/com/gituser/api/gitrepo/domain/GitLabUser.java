package com.gituser.api.gitrepo.domain;

import lombok.Data;


@Data
public class GitLabUser {
    private String repositoryName;
    private Integer id;

    public GitLabUser(String repositoryName, Integer id) {
        this.repositoryName = repositoryName;
        this.id = id;
    }
}
