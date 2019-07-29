# git-repo
Project to search a git user and his/her repositories on GitHub and GitLab.

Project has a REST end point which takes a GitHub & GitLab user name and returns a list of user's repositories on GitHub and Gitlab with Repository id, git host and name.<br/>
A filter(optional) can be applied to only fetch the repositories owned by the user.
<pre>https://api.github.com </br>
https://gitlab.com/api/v4 </pre>

* **URL**

    http://localhost:8080/repo/api/v1/:userName
* **Method:**

    GET
* **URL Params:**
 
   `userName` : *Required* User whose repositories need to be fetched.<br />
   `repositoryOwner`: Filter to only fetch the repositories owned by the user. <br />
  
* **Sample URL:**  
    <pre>http://localhost:8080/repo/api/v1/UmangCoditas?repositoryOwner=true</pre>
    
* **Success Response:**
 * **Code:** 200 <br />
    **Content:** 
    ```json
  [
      {
        "id": 199156002,
        "host": "GitHub",
        "name": "git-repo"
    },
    {
        "id": 197175868,
        "host": "GitHub",
        "name": "eshop"
    },
    {
        "id": 13460018,
        "host": "GitLab",
        "name": "gitlab-assignment1"
     }
    ]
    
* **Failure Response:**
    * **Code:** 400 Bad Request <br/>
    * **Code:** 500 Internal Server error <br/>

## Prerequisites
Softwares needs to be installed
  - Java 1.8
  - Apache Maven-3.6.1
  
## Libraries Used
  - Apache HttpClient » 4.5.9
  http://hc.apache.org/
  
  Apache HttpComponents Client library is used to invoke the Foursquare Places API from the custom end point.
  
  - lombok 
  Used Lombok  for  POJO classes getter-setter, constructors.

## Spring Boot
Created this project using Spring Boot 2.1.5 https://spring.io/projects/spring-boot. </br>
Spring Boot Reference Guide https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/htmlsingle/

##GitHub API
GitHub API to fetch repositories of GitLab user by user name.
https://api.github.com

##GitLab API
GitLab API to fetch repositories of GitLab user by user id.
https://gitlab.com/api/v4

## Build the project
To build the application run:
<pre> mvnw clean package </pre>

## Run
Run the application with the following command:
<pre> mvn spring-boot:run </pre>  OR 
<pre> java -jar target/git-repo-0.0.1-SNAPSHOT.jar </pre> 
## Testing
To launch your application's tests, run:
<pre> mvnw clean test </pre>
