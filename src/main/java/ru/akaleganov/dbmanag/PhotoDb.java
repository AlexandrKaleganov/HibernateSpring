package ru.akaleganov.dbmanag;

/**
 * @author Alexander Kalegano
 * @version 1
 * @since 19/05/19
 */
public class PhotoDb {
    private static final MarkaDb INSTANCE = new MarkaDb();

    public static MarkaDb getInstance() {
        return INSTANCE;
    }

}
