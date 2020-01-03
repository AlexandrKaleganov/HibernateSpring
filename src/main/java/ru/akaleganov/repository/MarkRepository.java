package ru.akaleganov.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.akaleganov.domain.Mark;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {

}
