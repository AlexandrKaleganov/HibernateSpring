package ru.akaleganov.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akaleganov.domain.Roles;
import ru.akaleganov.repository.RolesRepository;

import java.util.List;

import static org.apache.log4j.Logger.getLogger;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
@Service
public class RolesService implements Store<Roles> {
    private final static Logger LOGGER = getLogger(RolesService.class);
    private final RolesRepository rolesRepository;

    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    //мтод не раелизован
    @Override
    public Roles add(Roles roles) {
        return this.rolesRepository.save(roles);
    }

    //мтод не раелизован
    @Override
    public Roles delete(Roles roles) {
        error();
        return null;
    }

    //мтод не раелизован
    @Override
    public Roles edit(Roles roles) {
        error();
        return null;
    }

    @Override
    public List<Roles> findAll() {
        return (List<Roles>) this.rolesRepository.findAll();
    }

    //мтод не раелизован
    @Override
    public Roles findByID(Roles roles) {
        LOGGER.error(roles);
        return this.rolesRepository.findById(roles.getId()).orElse(new Roles(0));
    }

    //мтод не раелизован
    @Override
    public List<Roles> findByName(Roles roles) {
        error();
        return null;
    }

    @Override
    public Roles findByLoginPass(Roles roles) {
        error();
        return null;
    }

    @Override
    public Roles findByLogin(Roles roles) {
        error();
        return null;
    }
}
