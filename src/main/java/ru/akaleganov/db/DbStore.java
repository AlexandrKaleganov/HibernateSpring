package ru.akaleganov.db;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import ru.akaleganov.models.Item;
import ru.akaleganov.service.SFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.apache.log4j.LogManager.getLogger;

/**
 * класс для управления базой данных
 * в данном случае понядобилось только получать список лотов, и получать лот по id
 */
public class DbStore implements Store<Item> {
    /**
     * Logeer
     */
    private static final Logger LOGGER = getLogger(DbStore.class);
    /**
     * create session factory
     */
    private final static DbStore DB_STOREINSTANCE = new DbStore();
    private final static SFactory S_FACTORY = SFactory.getSfactory();

    public static DbStore getDbstoreINSTANCE() {
        return DB_STOREINSTANCE;
    }


    /**
     * findbyID objec
     * находит лот по id
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
     * получение списка всех лотов
     * @return
     */
    @Override
    public List<Item> findall() {
        ArrayList<Item> items = this.openandCloseSession(
                (session) -> (ArrayList<Item>) session.createQuery("from Item ").list()
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
            try (Session session = S_FACTORY.getFactory().openSession()) {
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
        return rsl;
    }
}
