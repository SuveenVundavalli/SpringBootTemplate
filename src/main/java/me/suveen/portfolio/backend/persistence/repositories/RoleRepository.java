package me.suveen.portfolio.backend.persistence.repositories;

import me.suveen.portfolio.backend.persistence.domain.backend.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/***
 * documentation @ https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 */

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
