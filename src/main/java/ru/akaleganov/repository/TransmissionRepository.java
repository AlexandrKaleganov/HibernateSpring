package ru.akaleganov.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.akaleganov.domain.Transmission;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
@Repository
public interface TransmissionRepository extends CrudRepository<Transmission, Long> {

}
