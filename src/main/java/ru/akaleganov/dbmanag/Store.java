package ru.akaleganov.dbmanag;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import ru.akaleganov.service.Sfactory;

import java.util.List;
import java.util.function.Function;

import static org.apache.log4j.LogManager.getLogger;

/**
 * @author Alexander Kaleganov
 * @since 17.05.2019
 * @param <E>
 */
public interface Store<E> {
    final static Sfactory S_FACTORY = Sfactory.getINSTANCE();
    final static Logger LOGGER = getLogger(Store.class);
    /**
     * refactor close factory and close session
     *
     * @param fank
     * @param <E>
     * @return
     */
    public default <E> E openandCloseSession(Function<Session, E> fank) {
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


    E add(E e);

    E delete(E e);

    E edit(E e);

    List<E> findAll();

    E findByID(E e);

    E findByName(E e);


}
