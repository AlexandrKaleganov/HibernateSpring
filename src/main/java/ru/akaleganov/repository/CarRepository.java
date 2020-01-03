package ru.akaleganov.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.akaleganov.domain.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    void deleteCarByAnnouncementId(Long anId);
}
