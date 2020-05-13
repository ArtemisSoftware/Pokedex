package com.artemissoftware.pokedex.repository;

public interface Repository<T> {

    void save(T item);

    void insert(T item);

    void update(T item);

    void delete(T item);

}