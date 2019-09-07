package ru.akaleganov.dbmanag;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import ru.akaleganov.service.Sfactory;

import java.util.List;
import java.util.function.Function;


/**
 * @param <E>
 * @author Alexander Kaleganov
 * @since 17.05.2019
 */
public interface Store<E> {
    Sfactory S_FACTORY = Sfactory.getINSTANCE();
    Logger LOGGER = Logger.getLogger(Store.class);

    /**
     * refactor close factory and close session
     *
     * @param fank
     * @param <E>
     * @return
     */
    default <E> E openSession(Function<Session, E> fank) {
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

    default void error() {
        try {
            throw new ExceptonnullMethod();
        } catch (ExceptonnullMethod e) {
            LOGGER.info(e.getMessage(), e);
        }
    }

    E add(E e);

    E delete(E e);

    E edit(E e);

    List<E> findAll();

    E findByID(E e);

    List<E> findByName(E e);

    E findByLoginPass(E e);

    E findByLogin(E e);

}
