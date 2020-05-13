package com.artemissoftware.pokedex.repository;

import io.reactivex.Single;

public interface Repository<T> {

    //Single<Long> save(T item);

    Single<Long> insert(T item);

    Single<Integer> update(T item);

    Single<Integer> delete(T item);

}