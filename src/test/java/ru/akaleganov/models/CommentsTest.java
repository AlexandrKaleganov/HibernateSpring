package ru.akaleganov.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.function.BiConsumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CommentsTest {
    public void testfank(BiConsumer<Comments, Session> fank) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Comments comments = new Comments();
        comments.setComment("test");
        fank.accept(comments, session);
        session.getTransaction().rollback();
        session.close();
        factory.close();
    }

    @Test
    public void modeltestAdd() {
        this.testfank((comments, ses) -> {
            ses.save(comments);
            Comments test = (Comments) ses.createQuery("from Comments where comment = 'test'").list().get(0);
            assertThat(test.getComment(), is("test"));
        });
    }

    @Test
    public void modelCommentsEdit() {
        this.testfank((comments, session) -> {
            session.save(comments);
            Comments test = (Comments) session.createQuery("from Comments where comment = 'test'").list().get(0);
            test.setComment("vasia");
            session.saveOrUpdate(test);
            Comments vasia = (Comments) session.createQuery("from Comments where comment = 'vasia'").list().get(0);
            assertThat(vasia.getComment(), is("vasia"));
        });
    }

    /**
     * обрати внимание на то что при добавление объекта возвращает
     * return event.getResultId(); что можно использовать в дальнейшем
     *
     * не удалось удалить через запрос
     * session.delete("delete from Commentss where id = ?", k);
     * java.lang.IllegalArgumentException: Unknown entity: java.lang.String
     */
    @Test
    public void modelCommentsDelete() {
        this.testfank((comments, session) -> {
            Integer k = (int) session.save(comments);
            comments.setId(k);
            Comments res = (Comments) session.createQuery("from Comments where id=:id ").setParameter("id", k).list().get(0);
            assertThat(res.getComment(), is("test"));
            session.delete(res);
            assertThat(session.createQuery("from Comments where id=:id ").setParameter("id", k).list().size(), is(0));
        });
    }
}