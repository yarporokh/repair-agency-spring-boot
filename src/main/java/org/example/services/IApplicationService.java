package org.example.services;

import org.example.models.Application;
import org.example.models.User;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * IApplicationService for connect repository with application controllers
 */
public interface IApplicationService {

    /**
     * Method returns all user applications
     * @param author user object
     * @return list of applications
     */
    @Transactional
    List<Application> findAllUserApplications(User author);

    /**
     * Method create new application in db
     * @param text text for application
     * @param user application author
     */
    @Transactional
    void newApp(String text, User user);

    /**
     * Method returns paginated applications
     * @param pageNum page of applications
     * @param sortField sorting by field
     * @param sortDir sorting by direction
     * @return page of applications
     */
    @Transactional
    Page<Application> findAll(int pageNum, String sortField, String sortDir);

    /**
     * Method saves application
     * @param application application for saving
     */
    @Transactional
    void save(Application application);
}
