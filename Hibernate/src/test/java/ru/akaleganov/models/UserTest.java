package ru.akaleganov.models;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserTest {
    public void testfank(BiConsumer<User, Session> fank) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        User user = new User();
        user.setLogin("test");
        fank.accept(user, session);
        session.getTransaction().rollback();
        session.close();
        factory.close();
    }

    @Test
    public void modeltestAdd() {
        this.testfank((user, ses) -> {
            ses.save(user);
            User test = (User) ses.createQuery("from User where login = 'test'").list().get(0);
            assertThat(test.getLogin(), is("test"));
        });
    }

    @Test
    public void modelUSerEdit() {
        this.testfank((user, session) -> {
            session.save(user);
            User test = (User) session.createQuery("from User where login = 'test'").list().get(0);
            test.setLogin("vasia");
            session.saveOrUpdate(test);
            User vasia = (User) session.createQuery("from User where login = 'vasia'").list().get(0);
            assertThat(vasia.getLogin(), is("vasia"));
        });
    }

    /**
     * обрати внимание на то что при добавление объекта возвращает
     * return event.getResultId(); что можно использовать в дальнейшем
     *
     * не удалось удалить через запрос
     * session.delete("delete from users where id = ?", k);
     * java.lang.IllegalArgumentException: Unknown entity: java.lang.String
     */
    @Test
    public void modelUSerDelete() {
        this.testfank((user, session) -> {
            Integer k = (int) session.save(user);
            user.setId(k);
            User res = (User) session.createQuery("from User where id=:id ").setParameter("id", k).list().get(0);
            assertThat(res.getLogin(), is("test"));
            session.delete(res);
            assertThat(session.createQuery("from User where id=:id ").setParameter("id", k).list().size(), is(0));
        });
    }
}