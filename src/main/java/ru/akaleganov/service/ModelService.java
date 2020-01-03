package ru.akaleganov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akaleganov.domain.Mark;
import ru.akaleganov.domain.Model;
import ru.akaleganov.repository.ModelRepository;

import java.util.List;

@Service
public class ModelService implements Store<Model> {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }


    @Override
    public Model add(Model model) {
        this.modelRepository.save(model);
        return model;
    }

    @Override
    public Model delete(Model model) {
        error();
        return null;
    }

    @Override
    public Model edit(Model model) {
        error();
        return null;
    }

    @Override
    public List<Model> findAll() {
        error();
        return null;
    }

    @Override
    public Model findByID(Model model) {
        error();
        return null;
    }

    @Override
    public List<Model> findByName(Model model) {
        error();
        return null;
    }

    public List<Model> findByMarkId(Mark mark) {
        return this.modelRepository.findAllByMarkId(mark.getId());
//        return null;
    }

    @Override
    public Model findByLoginPass(Model model) {
        error();
        return null;
    }

    @Override
    public Model findByLogin(Model model) {
        error();
        return null;
    }
}
