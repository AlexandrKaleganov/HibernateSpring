package ru.akaleganov.service;

import org.junit.Test;
import ru.akaleganov.models.Item;

import java.util.ArrayList;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DispatchTest {

    /**
     * test function
     *
     * @param fank
     */
    private void fanktest(Consumer<Item> fank) {
        Item item = new Item();
        item.setName("desc");
        item.setPrice(1);
        try {
            item = Dispatch.getInstance().access("add", item);
            fank.accept(item);
        } finally {
            Dispatch.getInstance().access("delete", item);
        }

    }

    /**
     * test findbyid
     */
    @Test
    public void testDispatcherbyID() {
        this.fanktest(item -> {
            Item testitem = Dispatch.getInstance().access("findbyid", item);
            assertThat(testitem.getName(), is("desc"));
        });
    }

    /**
     * test list
     */
    @Test
    public void testDispatcherList() {
        this.fanktest(item -> {
            ArrayList<Item> list = Dispatch.getInstance().access("list", item);
            assertThat(list.get(list.size() - 1).getName(), is("desc"));
        });
    }


}