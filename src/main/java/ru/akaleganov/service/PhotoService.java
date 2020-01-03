package ru.akaleganov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akaleganov.domain.Photo;
import ru.akaleganov.repository.PhotoRepository;

import java.util.List;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
@Service
public class PhotoService implements Store<Photo> {
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo add(Photo photo) {
        this.photoRepository.save(photo);
        return photo;
    }

    public List<Photo> add(List<Photo> photo) {
        this.photoRepository.saveAll(photo);
        return photo;
    }

    @Override
    public Photo delete(Photo photo) {
        this.photoRepository.delete(photo);
        return photo;
    }

    @Override
    public Photo edit(Photo photo) {
        error();
        return null;
    }

    @Override
    public List<Photo> findAll() {
        return null;
    }


    @Override
    public Photo findByID(Photo photo) {
        return this.photoRepository.findById(photo.getId()).orElse(new Photo());
    }

    @Override
    public List<Photo> findByName(Photo photo) {
        error();
        return null;
    }

    @Override
    public Photo findByLoginPass(Photo photo) {
        error();
        return null;
    }

    @Override
    public Photo findByLogin(Photo photos) {
        error();
        return null;
    }
}
