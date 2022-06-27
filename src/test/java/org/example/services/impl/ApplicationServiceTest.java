package org.example.services.impl;

import org.example.models.Application;
import org.example.models.User;
import org.example.repository.ApplicationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServiceTest {
    @Autowired
    ApplicationService applicationService;

    @MockBean
    ApplicationRepository applicationRepository;

    @Test
    public void findAllUserApplications() {
        User user = new User();

        Mockito.doReturn(new ArrayList<Application>())
                .when(applicationRepository)
                .findApplicationByAuthor(user);

        List<Application> applicationList = applicationService.findAllUserApplications(user);

        Mockito.verify(applicationRepository, Mockito.times(1)).findApplicationByAuthor(user);
    }

    @Test
    public void newApp() {
        User user = new User();
        applicationService.newApp("text", user);

        Mockito.verify(applicationRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void findAll() {
        Page<Application> page = applicationService.findAll(1, "applicationId", "asc");

        Mockito.verify(applicationRepository, Mockito.times(1)).findAll(ArgumentMatchers.any(Pageable.class));
    }

    @Test
    public void save() {
        Application application = new Application();
        applicationService.save(application);

        Mockito.verify(applicationRepository, Mockito.times(1)).save(application);
    }
}