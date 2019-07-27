package com.gituser.api.gitrepo.domain;

import lombok.Data;

@Data
public class Repository {
    private Integer id;
    private String host;
    private String name;

    public Repository(Integer id, String host, String name) {
        this.id = id;
        this.host = host;
        this.name = name;
    }
}
