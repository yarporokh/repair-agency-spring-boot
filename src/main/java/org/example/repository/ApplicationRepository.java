package org.example.repository;

import org.example.models.Application;
import org.example.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends PagingAndSortingRepository<Application, Long> {
    /**
     * Method returns list of users applications
     * @param author user model
     * @return list of applications
     */
    List<Application> findApplicationByAuthor(User author);
}
