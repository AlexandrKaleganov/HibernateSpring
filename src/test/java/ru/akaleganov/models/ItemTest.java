package ru.akaleganov.models;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
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
        Item item = new Item();
        item.setName("name");
        item.setPrice(1);
        item.setId((long) 12);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(item));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(item);
        try {
            Item itemtwo = new ObjectMapper().readValue(item.toString(), Item.class);
            System.out.println(itemtwo);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        this.testfank((item, ses) -> {
//            ses.save(item);
//            Item test = (Item) ses.createQuery("from Item where descr = 'хлеб'").list().get(0);
//            assertThat(test.getName(), is("test"));
//        });
    }

}