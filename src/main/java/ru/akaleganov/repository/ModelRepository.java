package ru.akaleganov.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.akaleganov.domain.Model;

import java.util.List;

@Repository
public interface ModelRepository extends CrudRepository<Model, Long> {
    List<Model> findAllByMarkId(Long markId);
}
