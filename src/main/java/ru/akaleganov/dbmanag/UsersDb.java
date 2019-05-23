package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
public class UsersDb implements Store<Users> {
    private static final UsersDb INSTANCE = new UsersDb();

    public static UsersDb getInstance() {
        return INSTANCE;
    }

    @Override
    public Users add(Users users) {
        return openSession(session -> {
            session.save(users);
            return session.load(Users.class, users.getId());
        });
    }

    @Override
    public Users delete(Users users) {
        return openSession(session -> {
            session.delete(users);
            return users;
        });
    }

    @Override
    public Users edit(Users users) {
        return openSession(session -> {
            session.saveOrUpdate(users);
            return session.load(Users.class, users.getId());
        });
    }

    @Override
    public List<Users> findAll() {
        return openSession(session -> session.createQuery("from Users").list());
    }

    @Override
    public Users findByID(Users users) {
        return openSession(session -> session.get(Users.class, users.getId()));
    }

    @Override
    public List<Users> findByName(Users users) {
        String sql = "from Users where name = '" + users.getName() + "'";
        return openSession(session -> session.createQuery(sql).list());
    }

    @Override
    public Users findByLoginPass(Users users) {
        String sql = "from Users where login = '" + users.getLogin() + "' and password = '" + users.getPassword() + "'";
        return refactList(sql);
    }

    @Override
    public Users findByLogin(Users users) {
        String sql = "from Users where login = '" + users.getLogin() + "'";
        return refactList(sql);
    }

    private Users refactList(String sql) {
        return openSession(session -> {
            ArrayList<Users> rsl = (ArrayList<Users>) session.createQuery(sql).list();
            if (rsl.size() > 0) {
                return rsl.get(0);
            } else {
                return new Users();
            }
        });
    }
}