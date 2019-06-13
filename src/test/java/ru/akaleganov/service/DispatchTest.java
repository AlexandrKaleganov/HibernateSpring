package ru.akaleganov.service;

import org.junit.Test;
import ru.akaleganov.models.Item;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * тест диспетчера управления базой данных
 */
public class DispatchTest {

    /**
     * tesFunction
     *
     * @param functionalMethod получаетс функциональный метод в котором происходит реализация тестирование
     */
    private void tesFunction(Consumer<Item> functionalMethod) {
        Item item = new Item();
        item.setId((long) 1);
        functionalMethod.accept(item);
    }

    /**
     * тестирование получение Лота по ID
     */
    @Test
    public void testingToGetTheObjectById() {
        this.tesFunction(item -> {
            Item testitem = Dispatch.getInstance().access("findById", item);
            assertThat(testitem.getName(), is("хлеб"));
        });
    }

    /**
     * тестирование получение списка всех Лотов
     */
    @Test
    public void testGettingAListOf() {
        this.tesFunction(item -> {
            ArrayList<Item> list = Dispatch.getInstance().access("list", item);
            assertThat(list.size(), is(3));
        });
    }


}