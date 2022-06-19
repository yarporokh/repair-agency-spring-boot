package org.example.repository;

import org.example.models.User;
import org.example.utils.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Method check if user with specific email exists in db
     * @param email user email
     * @return true if user exists in db
     */
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);

    /**
     * Methods returns list of users with specific role
     * @param role role for searching
     * @return list of users with specific role
     */
    List<User> findUsersByRoles(Role role);

    /**
     * Method returns user with specific email
     * @param username users email
     * @return user object
     */
    User findByEmail(String username);
}
