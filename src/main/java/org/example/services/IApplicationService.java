package org.example.services;

import org.example.models.Application;
import org.example.models.User;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IApplicationService {

    @Transactional
    List<Application> findAllUserApplications(User author);
    @Transactional
    void newApp(String text, User user);

    @Transactional
    Page<Application> findAll(int pageNum, String sortField, String sortDir);

    @Transactional
    void save(Application application);
}
