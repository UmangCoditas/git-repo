package com.gituser.api.gitrepo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gituser.api.gitrepo.domain.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryServiceImpl implements RepositoryService {
    private static final Logger log = LoggerFactory.getLogger(RepositoryServiceImpl.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private Environment environment;

    @Override
    public HttpGet generateRequestUri(String gitURL) {
        log.info("Generate URL and create HttpGet request instance for " + gitURL);
        HttpGet request = null;
        try {
            URIBuilder builder = new URIBuilder(gitURL);
            URI uri = builder.build();
            request = new HttpGet(uri);
            log.info("Generated Url : " + uri.toString());

        } catch (URISyntaxException e) {
            log.error("Error while generating Url - Request : " + e.getMessage());
        }
        return request;
    }

    @Override
    public List<GitHubRepository> getGitHubRepository(HttpGet request) {

        List<GitHubRepository> gitHubRepositoryList = Collections.emptyList();
        StringBuilder responseString = getHttpResponse(request);
        try {
            gitHubRepositoryList = mapper.readValue(responseString.toString(), new TypeReference<List<GitHubRepository>>() {
            });
        } catch (IOException e) {
            log.error("Error while reading JSON for gitHubRepositoryList " + e.getMessage());
        }
        return gitHubRepositoryList;
    }


    @Override
    public List<GitHubUser> getGitHubUserDetails(List<GitHubRepository> userRepositoryList, boolean filter, String userName) {
        log.info("Filter passed is ", filter);
        List<GitHubUser> gitHubUserList = new ArrayList<>();
        userRepositoryList.parallelStream().forEach(gitHubRepository -> gitHubUserList.add(new GitHubUser(gitHubRepository.getName(), gitHubRepository.getOwner().getLogin(), !gitHubRepository.getPrivate(), gitHubRepository.getId(),gitHubRepository.getFork())));
        if (filter) {
            log.info("Filter Repository ownership", filter);
            return gitHubUserList.parallelStream().filter(gitHubUser -> !gitHubUser.isForked())
                    .collect(Collectors.toList());
        }
        return gitHubUserList;
    }

    @Override
    public GitLabOwner getGitLabOwner(HttpGet request) {
        List<GitLabOwner> gitLabOwnerList = Collections.emptyList();
        StringBuilder responseString = getHttpResponse(request);
        try {
            gitLabOwnerList = mapper.readValue(responseString.toString(), new TypeReference<List<GitLabOwner>>() {
            });
        } catch (IOException e) {
            log.error("Error while reading JSON for gitLabOwnerList " + e.getMessage());
        }
        return gitLabOwnerList.get(0);
    }


    @Override
    public List<GitLabProject> getGitLabProject(HttpGet request) {
        List<GitLabProject> gitLabProjectList = Collections.emptyList();
        StringBuilder responseString = getHttpResponse(request);
        try {
            gitLabProjectList = mapper.readValue(responseString.toString(), new TypeReference<List<GitLabProject>>() {
            });
        } catch (IOException e) {
            log.error("Error while reading JSON for gitLabOwnerList " + e.getMessage());
        }
        return gitLabProjectList;
    }

    @Override
    public List<GitLabUser> getGitLabUserDetails(List<GitLabProject> gitLabProjectList, boolean filter, Integer userId) {
        log.info("Filter passed is ", filter);
        List<GitLabUser> gitlabUserList = new ArrayList<>();
        gitLabProjectList.parallelStream().forEach(gitLabProject -> gitlabUserList.add(new GitLabUser(gitLabProject.getName(), gitLabProject.getId())));
        return gitlabUserList;
    }

    private StringBuilder getHttpResponse(HttpGet request) {
        StringBuilder responseString = new StringBuilder();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpResponse response = httpClient.execute(request);
            String strResponse = "";
            BufferedReader rd = null;
            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));


            while ((strResponse = rd.readLine()) != null) {
                responseString.append(strResponse);
            }
            log.info("Http Response String is : {}", responseString.toString());


        } catch (ClientProtocolException e) {
            log.error("Error while fetching Http Response: " + e.getMessage());
        } catch (IOException e) {
            log.error("Error while fetching Http Response: " + e.getMessage());
        }
        return responseString;
    }
}
