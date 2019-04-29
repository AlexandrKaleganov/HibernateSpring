package ru.akaleganov.db;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.akaleganov.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.apache.log4j.LogManager.getLogger;

public class DbStore implements Store<Item> {
    /**
     * Logeer
     */
    private static final Logger LOGGER = getLogger(DbStore.class);
    /**
     * create session factory
     */
    private final static DbStore DB_STOREINSTANCE = new DbStore();

    public static DbStore getDbstoreINSTANCE() {
        return DB_STOREINSTANCE;
    }

    /**
     * add object
     *
     * @param item
     * @return
     */
    @Override
    public Item add(Item item) {
        return this.openandCloseSession(session ->
                session.get(Item.class, session.save(item))
        );
    }

    /**
     * delete item from data
     *
     * @param item
     */
    @Override
    public Item delete(Item item) {
        return this.openandCloseSession(session -> {
            session.delete(item);
            return item;
        });
    }

    /**
     * update object
     *
     * @param item
     * @return
     */
    @Override
    public Item update(Item item) {
        return this.openandCloseSession(session -> {
            session.update(item);
            return session.get(Item.class, item.getId());
        });
    }

    /**
     * findbyID objec
     *
     * @param item
     * @return
     */
    @Override
    public Item findbyID(Item item) {
        return this.openandCloseSession((session) ->
                session.get(Item.class, item.getId()));
    }

    /**
     * find ALL object
     *
     * @return
     */
    @Override
    public List<Item> findall() {
        ArrayList<Item> items = this.openandCloseSession(
                (session) -> (ArrayList<Item>) session.createQuery("from Item ").list()
        );
        return items;
    }

    @Override
    public List<Item> findallnotDone() {
        ArrayList<Item> items = this.openandCloseSession(
                (session) -> (ArrayList<Item>) session.createQuery("from Item where done = false").list()
        );
        return items;
    }

    /**
     * refactor close factory and close session
     *
     * @param fank
     * @param <E>
     * @return
     */
    private <E> E openandCloseSession(Function<Session, E> fank) {
        E rsl = null;
        try (SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();) {
            try (Session session = factory.openSession()) {
                try {
                    session.beginTransaction();
                    rsl = fank.apply(session);
                    session.getTransaction().commit();
                } catch (Exception e) {
                    session.getTransaction().rollback();
                    LOGGER.error(e.getMessage(), e);
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return rsl;
    }
}
