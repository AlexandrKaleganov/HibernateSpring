package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Announcement;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementDb implements Store<Announcement> {
    private static final AnnouncementDb INSTANCE = new AnnouncementDb();

    public static AnnouncementDb getInstance() {
        return INSTANCE;
    }

    @Override
    public Announcement add(Announcement announcement) {
        return openSession(session -> {
            session.save(announcement);
            return session.load(Announcement.class, announcement.getId());
        });
    }

    @Override
    public Announcement delete(Announcement announcement) {
        return openSession(se -> {
            se.createQuery("delete Photo where car_id = :id").setParameter("id", announcement.getCar().getId()).executeUpdate();
            se.createQuery("delete Car where announcement_id =  :id").setParameter("id", announcement.getId()).executeUpdate();
            se.createQuery("delete Announcement where id = :id").setParameter("id", announcement.getId()).executeUpdate();
            return announcement;
        });
    }

    @Override
    public Announcement edit(Announcement announcement) {
        return openSession(session -> {
            session.saveOrUpdate(announcement);
            return session.load(Announcement.class, announcement.getId());
        });
    }

    @Override
    public List<Announcement> findAll() {
        return this.refactList("from Announcement");
    }

    @Override
    public Announcement findByID(Announcement announcement) {
        Announcement rsl = openSession(session -> session.get(Announcement.class, announcement.getId()));
        if (rsl == null) {
            rsl = new Announcement(0);
        }
        return rsl;
    }

    @Override
    public List<Announcement> findByName(Announcement announcement) {
        String sql = "from Announcement where name = '" + announcement.getName() + "'";
        return this.refactList(sql);
    }

    private ArrayList<Announcement> refactList(String sql) {
        return openSession(session -> {
            ArrayList<Announcement> rsl = (ArrayList<Announcement>) session.createQuery(sql).list();
            if (rsl.size() > 0) {
                return rsl;
            } else {
                rsl.add(new Announcement(0));
                return rsl;
            }
        });
    }

    @Override
    public Announcement findByLoginPass(Announcement announcement) {
        error();
        return null;
    }

    @Override
    public Announcement findByLogin(Announcement announcement) {
        error();
        return null;
    }
}
