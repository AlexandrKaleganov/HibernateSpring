package ru.akaleganov.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.akaleganov.domain.Users;

import java.util.List;
import java.util.Optional;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByLogin(String login);
    Optional<Users> findByLoginAndPassword(String login, String password);
    List<Users> findAllByName(String name);

}
