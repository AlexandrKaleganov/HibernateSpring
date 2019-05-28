package ru.akaleganov.service;

import java.io.IOException;

public interface FankEx<E, R> {
    R submit(E e) throws IOException;
}
