package com.artemissoftware.pokedex.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import io.reactivex.Single;

@Dao
public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<Long> insert(T entity);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(T... entity);



    @Update(onConflict = OnConflictStrategy.IGNORE)
    Single<Integer> update(T entity);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(T... entity);


    @Delete
    Single<Integer> delete(T entity);
}
