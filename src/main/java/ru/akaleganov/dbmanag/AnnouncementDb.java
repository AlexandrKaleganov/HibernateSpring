package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Announcement;

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
        return openSession(session -> session.createQuery("from Announcement").list());
    }

    @Override
    public Announcement findByID(Announcement announcement) {
        return openSession(session -> session.get(Announcement.class, announcement.getId()));
    }

    @Override
    public List<Announcement> findByName(Announcement announcement) {
        String sql = "from Announcement where name = '" + announcement.getName() + "'";
        return openSession(se -> se.createQuery(sql).list());
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
