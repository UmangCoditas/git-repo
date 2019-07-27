package com.gituser.api.gitrepo.controller;

import com.gituser.api.gitrepo.service.RepositoryService;
import com.gituser.api.gitrepo.service.RepositoryServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class GitUserControllerTestConfiguration {
    @Bean
    public RepositoryService myHelper() {
        return new RepositoryServiceImpl();
    }
}
