package ru.akaleganov.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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

    public Item testfanktwoCommit(BiFunction<Item, Session, Item> fank) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setDescr("test");
        Item rsl = fank.apply(item, session);
        session.getTransaction().commit();
        session.close();
        factory.close();
        return rsl;
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
     * <p>
     * не удалось удалить через запрос
     * session.delete("delete from items where id = ?", k);
     * java.lang.IllegalArgumentException: Unknown entity: java.lang.String
     */
    @Test
    public void modelitemDelete() {
        this.testfank((item, session) -> {
            session.save(item);
            session.delete(item);
            assertThat(session.get(Item.class, item.getId()), is((Item) null));
        });
    }

    @Test
    public void testReferences() {
        Item item = new Item();
        item.setDescr("спасите помогите");

        //создадим админа и добавим его в бд
        User admin = new User();
        admin.setLogin("admin");
        //создадим пользователя добавим его в бд  и привяжем к нашей заявке
        User user = new User();
        user.setLogin("Саша");
        //создадим два комментария один будет от админа
        Comments commentsAdmin = new Comments();
        commentsAdmin.setComment("как вам помочь?");
        //второй комментарий от юзера
        Comments commentsUser = new Comments();
        commentsUser.setComment("а всё, сам разобрался, заяву можно закрывать");

        this.testfanktwoCommit((it, session) -> {
            //добавляем пользователей в бд
            session.save(admin);
            session.save(user);

            //добавим заявку в бд
            item.setAutor(user);
            session.save(item);

            //добавим комментарий от админа в БД
            commentsAdmin.setAutor(admin);
            commentsAdmin.setItem(item);
            session.save(commentsAdmin);

            //добавим комментарий от админа в БД
            commentsUser.setAutor(user);
            commentsUser.setItem(item);
            session.save(commentsUser);
            final Item item1 = session.load(Item.class, item.getId());
            /**
             *
             * ТЕСТЫ!!!
             *
             * */

            //попытка получить коментарии из объекта до коммита получим нулл
            assertThat(item1.getComments(), is((Comments) null));
            //попытка получить коментарии до коммита, сделав запрос в бд полуили нулл
            assertThat(session.get(Item.class, item.getId()).getComments(), is((Comments) null));
            return item1;
        });

        //попытка получить коментарии из объекта после коммита получим нулл
        //сделал вывод что  изменение данных в других таблицах не влияет на содержимое сумки объекта
        assertThat(item.getComments(), is((Comments) null));

        Item it = this.testfanktwoCommit((i, ses) -> {
            //попытка получить коментарии после коммита, сделав запрос в бд
            final Item rsl = ses.load(Item.class, item.getId());
            assertThat(rsl.getComments().get(0).getComment(), is("как вам помочь?"));
            assertThat(rsl.getComments().get(1).getComment(), is("а всё, сам разобрался, заяву можно закрывать"));
            System.out.println(ses.createQuery("from Item where id=:id").setParameter("id", rsl.getId()));
            //попробуем удалить объекты из бд
            //не получилось, пишет javax.persistence.EntityExistsException:
            // A different object with the same identifier value was already
            // associated with the session : [ru.akaleganov.models.Comments#66]
            Item testdel = new Item();
            testdel.setId(rsl.getId());
            testdel.setDescr(rsl.getDescr());
            testdel.setAutor(rsl.getAutor());
            testdel.setComments(rsl.getComments());
            ses.delete(rsl);
            ses.delete(rsl.getComments().get(0).getAutor());
            ses.delete(rsl.getComments().get(1).getAutor());
            return rsl;
        });
        System.out.println(it);
        System.out.println(it.getComments());
    }
}