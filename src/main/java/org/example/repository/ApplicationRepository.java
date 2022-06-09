package org.example.repository;

import org.example.models.Application;
import org.example.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    List<Application> findApplicationByAuthor(User author);
}
