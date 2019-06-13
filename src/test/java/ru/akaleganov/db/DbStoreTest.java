package ru.akaleganov.db;

import org.junit.Test;
import ru.akaleganov.models.Item;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * тестирование DbStore
 */

public class DbStoreTest {

    public void testfank(Item item, BiConsumer<Item, DbStore> fank) {
        final DbStore store = (DbStore) DbStore.getDbStore();
        item.setId((long) 1);
        fank.accept(item, store);
    }

    /**
     * findbyid test
     */
    @Test
    public void findbyid() {
        this.testfank(new Item(), (item, dbStore) -> {
            Item rsl = dbStore.findById(item);
            assertThat(rsl.getName(), is("хлеб"));
        });
    }

    /**
     * findall test
     */
    @Test
    public void findall() {
        this.testfank(new Item(), (item, dbStore) -> {
            ArrayList<Item> list = (ArrayList<Item>) dbStore.findAll();
            assertThat(list.size(), is(3));
        });
    }
}