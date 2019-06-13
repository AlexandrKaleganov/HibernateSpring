package ru.akaleganov.db;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import ru.akaleganov.models.Item;
import ru.akaleganov.service.SFactory;

import java.util.List;
import java.util.function.Function;

import static org.apache.log4j.LogManager.getLogger;

/**
 * @author Alexander Kaleganov
 * @see DbStore#LOGGER
 * класс для управления базой данных
 * в данном случае понядобилось только получать список лотов, и получать лот по id
 * @since 13/06/2019
 * <br/>
 * <b>класс содержит поля</b>
 */
public class DbStore implements Store<Item> {
    /**
     * Логер
     */
    private static final Logger LOGGER = getLogger(DbStore.class);

    private final static DbStore DB_STORE = new DbStore();
    /**
     * получение фабрики сессий
     */
    private final static SFactory S_FACTORY = SFactory.getSfactory();

    public static DbStore getDbStore() {
        return DB_STORE;
    }


    /**
     * находит лот по id
     *
     * @param item {@link Item}
     * @return {@link Item}
     */
    @Override
    public Item findById(Item item) {
        return this.openingAndClosingOfASession((session) ->
                session.get(Item.class, item.getId()));
    }

    /**
     * find ALL object
     * получение списка всех лотов
     *
     * @return {@link  Item} получение списка всех лотов
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findAll() {
        return this.openingAndClosingOfASession(
                (session) -> session.createQuery("from Item ").list()
        );
    }

    /**
     * refactor close factory and close session
     *
     * @param function {@link Function} функциональный интерфейс, который будет принимать сессию и возвращать объект
     * @param <E> получение объекта в результате выполнения функции
     * @return {@link Item} получим итем или список итемов
     */

    private <E> E openingAndClosingOfASession(Function<Session, E> function) {
        E rsl = null;
        try (Session session = S_FACTORY.getFactory().openSession()) {
            try {
                session.beginTransaction();
                rsl = function.apply(session);
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
