package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Users;

import java.util.List;

public class UsersDb implements Store<Users> {
    private static final UsersDb INSTANCE = new UsersDb();

    public static UsersDb getInstance() {
        return INSTANCE;
    }

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
            return session.load(Users.class, users.getId());
        });
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
        String sql = "from Users where name = '" + users.getName() + "'";
        return (Users) openandCloseSession(session -> session.createQuery(sql).list().get(0));
    }
    @Override
    public Users findByLogin(Users users) {
        String sql = "from Users where login = '" + users.getLogin() + "'";
        return (Users) openandCloseSession(session -> session.createQuery(sql).list().get(0));
    }
}
