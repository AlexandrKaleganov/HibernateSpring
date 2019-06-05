package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
public class PhotoDb implements Store<Photo> {
    private static final PhotoDb INSTANCE = new PhotoDb();

    public static PhotoDb getInstance() {
        return INSTANCE;
    }

    @Override
    public Photo add(Photo photo) {
        return openSession(session -> {
            session.save(photo);
            return photo;
        });
    }

    @Override
    public Photo delete(Photo photo) {
        return openSession(session -> {
            session.delete(photo);
            return photo;
        });
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
        Photo rsl = openSession(session -> session.get(Photo.class, photo.getId()));
        if (rsl == null) {
            rsl = new Photo();
        }
        return rsl;
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
