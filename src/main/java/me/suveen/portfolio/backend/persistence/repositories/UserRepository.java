package me.suveen.portfolio.backend.persistence.repositories;

import me.suveen.portfolio.backend.persistence.domain.backend.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * documentation @ https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Returns a User given Username or null if not found
     * @param username the username
     * @return a User given Username or null if not found
     */
    public User findByUsername(String username);

     /**
     * Returns a User given Username or null if not found
     * @param email the email
     * @return a User given email or null if not found
     */
    public User findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.password = :password where u.id = :userId")
    public void updateUserPassword(@Param("userId") long userId, @Param("password") String password);


}
