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
public class PhotoDb implements Store<List<Photo>> {
    private static final PhotoDb INSTANCE = new PhotoDb();

    public static PhotoDb getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Photo> add(List<Photo> photo) {
        return openSession(session -> {
                    List<Photo> rsl = new ArrayList<>();
                    photo.forEach(e -> {
                        session.save(e);
                        rsl.add(e);
                    });
                    return rsl;
                }
        );
    }

    @Override
    public List<Photo> delete(List<Photo> photo) {
        return openSession(session -> {
            List<Photo> list = null;
            photo.forEach(session::delete);
            return photo;
        });
    }

    @Override
    public List<Photo> edit(List<Photo> photo) {
        error();
        return null;
    }

    @Override
    public List<List<Photo>> findAll() {
        return null;
    }


    @Override
    public List<Photo> findByID(List<Photo> photo) {
        error();
        return null;
    }

    @Override
    public List<Photo> findByName(List<Photo> photo) {
        error();
        return null;
    }

    @Override
    public List<Photo> findByLogin(List<Photo> photo) {
        error();
        return null;
    }
}
