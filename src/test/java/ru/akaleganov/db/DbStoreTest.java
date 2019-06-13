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
    /**
     * tesFunction
     *
     * @param functionalMethod получаетс функциональный метод в котором происходит реализация тестирование
     */
    private void tesFunction(Item item, BiConsumer<Item, DbStore> functionalMethod) {
        final DbStore store = DbStore.getDbStore();
        item.setId((long) 1);
        functionalMethod.accept(item, store);
    }

    /**
     * тестирование получение Лота по ID
     */
    @Test
    public void testingToGetTheObjectById() {
        this.tesFunction(new Item(), (item, dbStore) -> {
            Item rsl = dbStore.findById(item);
            assertThat(rsl.getName(), is("хлеб"));
        });
    }

    /**
     * тестирование получение списка всех Лотов
     */
    @Test
    public void testGettingAListOf() {
        this.tesFunction(new Item(), (item, dbStore) -> {
            ArrayList<Item> list = (ArrayList<Item>) dbStore.findAll();
            assertThat(list.size(), is(3));
        });
    }
}