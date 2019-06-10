package ru.akaleganov.db;

import org.junit.Test;
import ru.akaleganov.models.Item;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class DbStoreTest {

    public void testfank(Item item, BiConsumer<Item, DbStore> fank) {
        final DbStore store = (DbStore) DbStore.getDbstoreINSTANCE();
        item.setName("desc");
        try {
            fank.accept(item, store);
        } finally {
            store.delete(item);
        }

    }

    /**
     * add test
     */
    @Test
    public void add() {
        this.testfank(new Item(), (item, dbStore) -> {
            Item rsl = dbStore.add(item);
            assertThat(rsl.getName(), is("desc"));
        });
    }


    /**
     * findbyid test
     */
    @Test
    public void findbyID() {
        this.testfank(new Item(), (item, dbStore) -> {
            Item rsl = dbStore.add(item);
            assertThat(dbStore.findbyID(rsl).getName(), is(rsl.getName()));
        });
    }

    @Test
    public void findall() {
        this.testfank(new Item(), (item, dbStore) -> {
            dbStore.add(item);
            ArrayList<Item> list = (ArrayList<Item>) dbStore.findall();
            assertThat(list.get(list.size() - 1).getName(), is(item.getName()));
        });
    }

    /**
     * получение заявок которые не выполнены
     */
}