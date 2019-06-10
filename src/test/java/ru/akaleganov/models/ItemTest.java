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
        fank.accept(item, session);
        session.getTransaction().rollback();
        session.close();
        factory.close();
    }

    @Test
    public void modeltestAdd() {
//        this.testfank((item, ses) -> {
//            ses.save(item);
//            Item test = (Item) ses.createQuery("from Item where descr = 'хлеб'").list().get(0);
//            assertThat(test.getName(), is("test"));
//        });
    }

}