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
        Item item = new ServiceItem().addItem("desc", "true");
        try {
            item = Dispatch.getInstance().access("add", item, new Item());
            fank.accept(item);
        } finally {
            Dispatch.getInstance().access("delete", item, new Item());
        }

    }

    /**
     * test findbyid
     */
    @Test
    public void testDispatcherbyID() {
        this.fanktest(item -> {
            assertThat(Dispatch.getInstance().access("findbyid", item, new Item()).getDescr(), is("desc"));
        });
    }

    /**
     * test list
     */
    @Test
    public void testDispatcherList() {
        this.fanktest(item -> {
            ArrayList<Item> list = Dispatch.getInstance().access("list", item, new ArrayList<Item>());
            assertThat(list.get(list.size() - 1).getDescr(), is("desc"));
        });
    }

    /**
     * test list isdone
     */
    @Test
    public void testDispatcherListisDone() {
        this.fanktest(item -> {
            Dispatch.getInstance().access("listnotDone", new Item(), new ArrayList<Item>())
                    .forEach(it -> assertThat(it.getDone(), is(false)));
        });
    }

    /**
     * test update
     */
    @Test
    public void testDispatcherUpdate() {
        this.fanktest(item -> {
            item.setDescr("test");
            Item expected = Dispatch.getInstance().access("update", item, new Item());
            assertThat(expected.getDescr(), is("test"));
        });
    }
}