package ru.akaleganov.models;

import java.util.Objects;

/**
 * модель Лот
 *
 * @author Alexander Kaleganov
 * @see Item#id
 * @see Item#name
 * @see Item#price
 * @since 13/06/2019
 * <br/>
 * <b>класс содержит поля:</b>
 */
public class Item {
    /**
     * id лота
     */
    private Long id;
    /**
     * наименование
     */
    private String name;
    /**
     * цена лота
     */
    private int price;

    public Item(Long id) {
        this.id = id;
    }

    public Item() {
    }

    /**
     * @return {@link Item#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id {@link Item#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return {@link Item#name}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name {@link Item#name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return {@link Item#price}
     */
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":\"" + id + "\", \"name\":\"" + name + "\", \"price\":\"" + price + "\"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return price == item.price && id.equals(item.id) && name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
