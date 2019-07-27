package com.gituser.api.gitrepo.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "description",
        "name",
        "name_with_namespace",
        "path",
        "path_with_namespace",
        "created_at",
        "default_branch",
        "tag_list",
        "ssh_url_to_repo",
        "http_url_to_repo",
        "web_url",
        "readme_url",
        "avatar_url",
        "star_count",
        "forks_count",
        "last_activity_at",
        "namespace"
})
public class GitLabProject implements Serializable {

    public GitLabProject(){
    }

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("name_with_namespace")
    private String nameWithNamespace;
    @JsonProperty("path")
    private String path;
    @JsonProperty("path_with_namespace")
    private String pathWithNamespace;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("default_branch")
    private String defaultBranch;
    @JsonProperty("tag_list")
    private List<Object> tagList = null;
    @JsonProperty("ssh_url_to_repo")
    private String sshUrlToRepo;
    @JsonProperty("http_url_to_repo")
    private String httpUrlToRepo;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("readme_url")
    private String readmeUrl;
    @JsonProperty("avatar_url")
    private Object avatarUrl;
    @JsonProperty("star_count")
    private Integer starCount;
    @JsonProperty("forks_count")
    private Integer forksCount;
    @JsonProperty("last_activity_at")
    private String lastActivityAt;
    @JsonProperty("namespace")
    private Namespace namespace;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("name_with_namespace")
    public String getNameWithNamespace() {
        return nameWithNamespace;
    }

    @JsonProperty("name_with_namespace")
    public void setNameWithNamespace(String nameWithNamespace) {
        this.nameWithNamespace = nameWithNamespace;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("path_with_namespace")
    public String getPathWithNamespace() {
        return pathWithNamespace;
    }

    @JsonProperty("path_with_namespace")
    public void setPathWithNamespace(String pathWithNamespace) {
        this.pathWithNamespace = pathWithNamespace;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("default_branch")
    public String getDefaultBranch() {
        return defaultBranch;
    }

    @JsonProperty("default_branch")
    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    @JsonProperty("tag_list")
    public List<Object> getTagList() {
        return tagList;
    }

    @JsonProperty("tag_list")
    public void setTagList(List<Object> tagList) {
        this.tagList = tagList;
    }

    @JsonProperty("ssh_url_to_repo")
    public String getSshUrlToRepo() {
        return sshUrlToRepo;
    }

    @JsonProperty("ssh_url_to_repo")
    public void setSshUrlToRepo(String sshUrlToRepo) {
        this.sshUrlToRepo = sshUrlToRepo;
    }

    @JsonProperty("http_url_to_repo")
    public String getHttpUrlToRepo() {
        return httpUrlToRepo;
    }

    @JsonProperty("http_url_to_repo")
    public void setHttpUrlToRepo(String httpUrlToRepo) {
        this.httpUrlToRepo = httpUrlToRepo;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonProperty("readme_url")
    public String getReadmeUrl() {
        return readmeUrl;
    }

    @JsonProperty("readme_url")
    public void setReadmeUrl(String readmeUrl) {
        this.readmeUrl = readmeUrl;
    }

    @JsonProperty("avatar_url")
    public Object getAvatarUrl() {
        return avatarUrl;
    }

    @JsonProperty("avatar_url")
    public void setAvatarUrl(Object avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonProperty("star_count")
    public Integer getStarCount() {
        return starCount;
    }

    @JsonProperty("star_count")
    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    @JsonProperty("forks_count")
    public Integer getForksCount() {
        return forksCount;
    }

    @JsonProperty("forks_count")
    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }

    @JsonProperty("last_activity_at")
    public String getLastActivityAt() {
        return lastActivityAt;
    }

    @JsonProperty("last_activity_at")
    public void setLastActivityAt(String lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    @JsonProperty("namespace")
    public Namespace getNamespace() {
        return namespace;
    }

    @JsonProperty("namespace")
    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


