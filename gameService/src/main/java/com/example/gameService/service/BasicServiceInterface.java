package com.example.gameService.service;

import java.util.List;
import java.util.Optional;

/**
 * Basic Service Interface basic Create, Read and Update operations need to be defined.
 * However, Delete operations can optionally be implemented.
 * @param <T>
 * @param <ID>
 */
public interface BasicServiceInterface<T, ID> {
    T save(T t);
    Iterable<T> findAll();
    Optional<T> findById(ID id);
    boolean existsById(ID id);
    Iterable<T> findAllById(Iterable<ID> ids);

    default long count() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default void deleteById(ID id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default void delete(T entity) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default void deleteAllById(Iterable<? extends ID> ids) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default void deleteAll(Iterable<? extends T> entities) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default void deleteAll() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }
}
