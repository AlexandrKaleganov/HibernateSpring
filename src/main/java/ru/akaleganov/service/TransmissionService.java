package ru.akaleganov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akaleganov.domain.Transmission;
import ru.akaleganov.repository.TransmissionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
@Service
public class TransmissionService implements Store<Transmission> {
    private final TransmissionRepository transmissionRepository;

    @Autowired
    public TransmissionService(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    @Override
    public Transmission add(Transmission transmission) {
        return this.transmissionRepository.save(transmission);
    }

    @Override
    public Transmission delete(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public Transmission edit(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public List<Transmission> findAll() {
        return (List<Transmission>) this.transmissionRepository.findAll();
    }

    @Override
    public Transmission findByID(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public List<Transmission> findByName(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public Transmission findByLoginPass(Transmission transmission) {
        error();
        return null;
    }

    @Override
    public Transmission findByLogin(Transmission transmission) {
        error();
        return null;
    }
}
