package com.gituser.api.gitrepo.controller;

import com.gituser.api.gitrepo.domain.*;
import com.gituser.api.gitrepo.service.RepositoryService;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyList;


import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(GitUserController.class)
@ContextConfiguration(classes = {GitUserControllerTestConfiguration.class})
public class GitUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoryService repositoryService;

    @Mock
    HttpGet request;

    @Test
    public void getRepositoryList() throws Exception {
        List<Repository> repositoryList = new ArrayList<>();
        repositoryList.add(new Repository(197175868, "GitHub", "eshop"));
        repositoryList.add(new Repository(13518460, "GitLab", "ci-test"));

        List<GitLabProject> gitLabProjectList = new ArrayList<>();
        GitLabProject gitLabProject = new GitLabProject();
        gitLabProject.setId(13518460);
        gitLabProject.setName("ci-test");
        gitLabProjectList.add(gitLabProject);

        List<GitHubRepository> gitHubRepositoryList = new ArrayList<>();
        GitHubRepository gitHubRepository = new GitHubRepository();
        gitHubRepository.setId(197175868);

        gitHubRepositoryList.add(gitHubRepository);

        List<GitHubUser> gitHubUserList = new ArrayList<>();
        gitHubUserList.add(new GitHubUser("eshop", "UmangCoditas", true, 197175868, false));

        List<GitLabUser> gitLabUserList = new ArrayList<>();
        gitLabUserList.add(new GitLabUser("ci-test", 13518460));

        GitLabOwner gitLabOwner = new GitLabOwner();
        gitLabOwner.setId(13518460);

        when(repositoryService.getGitLabOwner(any())).thenReturn(gitLabOwner);

        when(repositoryService.generateRequestUri(anyString())).thenReturn(request);

        when(
                repositoryService.getGitLabProject(any())).thenReturn(gitLabProjectList);

        when(
                repositoryService.getGitLabUserDetails(anyList(), anyBoolean(), anyInt())).thenReturn(gitLabUserList);

        when(
                repositoryService.getGitHubRepository(any())).thenReturn(gitHubRepositoryList);

        when(
                repositoryService.getGitHubUserDetails(anyList(), anyBoolean(), anyString())).thenReturn(gitHubUserList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/repo/api/v1/UmangCoditas").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"id\":197175868,\"host\":\"GitHub\",\"name\":\"eshop\"},{\"id\":13518460,\"host\":\"GitLab\",\"name\":\"ci-test\"}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);


    }
}