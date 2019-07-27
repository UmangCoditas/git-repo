package com.gituser.api.gitrepo;

import com.gituser.api.gitrepo.controller.GitUserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitRepoApplicationTests {

    @Autowired
    GitUserController gitUserController;

    @Test
    public void contextLoads() {
        assertThat(gitUserController).isNotNull();
    }

}
