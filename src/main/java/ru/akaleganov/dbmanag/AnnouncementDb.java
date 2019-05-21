package ru.akaleganov.dbmanag;

import ru.akaleganov.modelsannot.Announcement;

import java.util.List;

public class AnnouncementDb implements Store<Announcement> {

    @Override
    public Announcement add(Announcement announcement) {
        return openSession(session -> {
            session.save(announcement);
            return session.load(Announcement.class, announcement.getId());
        });
    }

    @Override
    public Announcement delete(Announcement announcement) {
        return openSession(session -> {
            session.delete(announcement);
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
    public Announcement findByLogin(Announcement announcement) {
        return null;
    }
}
