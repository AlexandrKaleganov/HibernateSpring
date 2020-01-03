package ru.akaleganov.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.akaleganov.domain.Photo;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {
    void deleteAllByCarId(Long carId);
}
