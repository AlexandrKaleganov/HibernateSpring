package ru.akaleganov.db;

import org.junit.Test;
import ru.akaleganov.models.Item;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class DbStoreTest {
    private final DbStore store = (DbStore) DbStore.getDbstoreINSTANCE();

    public void testfank(Item item, BiConsumer<Item, DbStore> fank) {
        item.setDescr("desc");
        try {
            fank.accept(item, this.store);
        } finally {
            this.store.delete(item);
        }

    }

    /**
     * add test
     */
    @Test
    public void add() {
        this.testfank(new Item(), (item, dbStore) -> {
            Item rsl = dbStore.add(item);
            assertThat(rsl.getDescr(), is("desc"));
        });
    }

    /**
     * update test
     */
    @Test
    public void update() {
        this.testfank(new Item(), (item, dbStore) -> {
            Item rsl = dbStore.add(item);
            rsl.setDescr("desc2");
            dbStore.update(rsl);
            rsl = dbStore.findbyID(rsl);
            assertThat(rsl.getDescr(), is("desc2"));
        });
    }

    /**
     * findbyid test
     */
    @Test
    public void findbyID() {
        this.testfank(new Item(), (item, dbStore) -> {
            Item rsl = dbStore.add(item);
            assertThat(dbStore.findbyID(rsl).getDescr(), is(rsl.getDescr()));
        });
    }

    @Test
    public void findall() {
        this.testfank(new Item(), (item, dbStore) -> {
            dbStore.add(item);
            ArrayList<Item> list = (ArrayList<Item>) dbStore.findall();
            assertThat(list.get(list.size() - 1).getDescr(), is(item.getDescr()));
        });
    }

    /**
     * получение заявок которые не выполнены
     */
    @Test
    public void findallnotDone() {
        this.testfank(new Item(), (item, dbStore) -> {
            dbStore.add(item);
            ArrayList<Item> listnotDone = (ArrayList<Item>) dbStore.findallnotDone();
            listnotDone.forEach(it -> assertThat(it.getDone(), is(false)));
        });
    }
}