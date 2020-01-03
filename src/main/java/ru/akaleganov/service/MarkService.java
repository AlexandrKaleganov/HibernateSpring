package ru.akaleganov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akaleganov.domain.Mark;
import ru.akaleganov.repository.MarkRepository;

import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
@Service
public class MarkService implements Store<Mark> {
    private final MarkRepository markRepository;

    @Autowired
    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }


    @Override
    public Mark add(Mark mark) {
        this.markRepository.save(mark);
        return mark;
    }

    @Override
    public Mark delete(Mark mark) {
        error();
        return null;
    }

    @Override
    public Mark edit(Mark mark) {
        error();
        return null;
    }

    @Override
    public List<Mark> findAll() {
        return (List<Mark>) this.markRepository.findAll();
    }

    @Override
    public Mark findByID(Mark mark) {
        return this.markRepository.findById(mark.getId()).orElse(new Mark());
    }

    @Override
    public List<Mark> findByName(Mark mark) {
        error();
        return null;
    }

    @Override
    public Mark findByLoginPass(Mark mark) {
        error();
        return null;
    }

    @Override
    public Mark findByLogin(Mark mark) {
        error();
        return null;
    }
}
