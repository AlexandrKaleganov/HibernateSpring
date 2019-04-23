package ru.akaleganov.models;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.function.BiConsumer;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * тестирование работоспособности модели в базе данных
 */
public class ItemTest {
    public void testfank(BiConsumer<Item, Session> fank) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setDescr("test");
        fank.accept(item, session);
        session.getTransaction().rollback();
        session.close();
        factory.close();
    }

    @Test
    public void modeltestAdd() {
        this.testfank((item, ses) -> {
            ses.save(item);
            Item test = (Item) ses.createQuery("from Item where descr = 'test'").list().get(0);
            assertThat(test.getDescr(), is("test"));
        });
    }

    @Test
    public void modelitemEdit() {
        this.testfank((item, session) -> {
            session.save(item);
            Item test = (Item) session.createQuery("from Item where descr = 'test'").list().get(0);
            test.setDescr("vasia");
            session.saveOrUpdate(test);
            Item vasia = (Item) session.createQuery("from Item where descr = 'vasia'").list().get(0);
            assertThat(vasia.getDescr(), is("vasia"));
        });
    }

    /**
     * обрати внимание на то что при добавление объекта возвращает
     * return event.getResultId(); что можно использовать в дальнейшем
     *
     * не удалось удалить через запрос
     * session.delete("delete from items where id = ?", k);
     * java.lang.IllegalArgumentException: Unknown entity: java.lang.String
     */
    @Test
    public void modelitemDelete() {
        this.testfank((item, session) -> {
            Long k = (long) session.save(item);
            item.setId(k);
            Item res = (Item) session.createQuery("from Item where id=:id ").setParameter("id", k).list().get(0);
            assertThat(res.getDescr(), is("test"));
            session.delete(res);
            assertThat(session.createQuery("from Item where id=:id ").setParameter("id", k).list().size(), is(0));
        });
    }
}