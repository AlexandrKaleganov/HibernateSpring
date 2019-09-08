package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Users;

import java.util.ArrayList;
import java.util.List;

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
        Users test = this.findByLogin(users);
        if (test.getId() != 0) {
            return users;
        } else {
            return openSession(session -> {
                session.save(users);
                return session.get(Users.class, users.getId());
            });
        }
    }

    @Override
    public Users delete(Users users) {
        return openSession(session -> {
            Users rsl = session.get(Users.class, users.getId());
            session.delete(rsl);
            return rsl;
        });
    }

    @Override
    public Users edit(Users users) {
        return openSession(session -> {
            session.saveOrUpdate(users);
            return session.get(Users.class, users.getId());
        });
    }

    @Override
    public List<Users> findAll() {
        return openSession(session -> session.createQuery("from Users").list());
    }

    @Override
    public Users findByID(Users users) {
        Users rsl = null;
        rsl = openSession(session -> session.get(Users.class, users.getId()));
        if (rsl == null) {
            rsl = new Users(0);
        }
        return rsl;
    }

    @Override
    public List<Users> findByName(Users users) {
        String sql = "from Users where name = '" + users.getName() + "'";
        return refactList(sql);
    }

    @Override
    public Users findByLoginPass(Users users) {
        String sql = "from Users where login = '" + users.getLogin() + "' and password = '" + users.getPassword() + "'";
        return refactList(sql).get(0);
    }

    @Override
    public Users findByLogin(Users users) {
        String sql = "from Users where login = '" + users.getLogin() + "'";
        return refactList(sql).get(0);
    }

    private ArrayList<Users> refactList(String sql) {
        return openSession(session -> {
            ArrayList<Users> rsl = (ArrayList<Users>) session.createQuery(sql).list();
            if (rsl.size() > 0) {
                return rsl;
            } else {
                rsl.add(new Users(0));
                return rsl;
            }
        });
    }
}
