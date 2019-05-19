package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Users;

import java.util.List;

public class UsersDb implements Store<Users> {

    @Override
    public Users add(Users users) {
        return openandCloseSession(session -> {
            session.save(users);
            return session.load(Users.class, users.getId());
        });
    }

    @Override
    public Users delete(Users users) {
        return openandCloseSession(session -> {
            session.delete(users);
            return users;
        });
    }

    @Override
    public Users edit(Users users) {
        return openandCloseSession(session -> {
            session.saveOrUpdate(users);
            return session.load(Users.class, users.getId());});
    }

    @Override
    public List<Users> findAll() {
        return openandCloseSession(session -> session.createQuery("from Users").list());
    }

    @Override
    public Users findByID(Users users) {
        return openandCloseSession(session -> session.get(Users.class, users.getId()));
    }

    @Override
    public Users findByName(Users users) {
        error();
        return null;
    }
}
