package com.jiahaoliuliu.chutoro.storagelayer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public abstract class CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insert(PersistentCurrency persistentCurrency);

    @Query("Select * from " + PersistentCurrency.TABLE_NAME + " where id == :id")
    public abstract PersistentCurrency getPersistentCurrencyById(long id);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    public abstract int update(PersistentCurrency persistentCurrency);

    @Delete
    public abstract void delete(PersistentCurrency persistentCurrency);

    @Query("Delete from " + PersistentCurrency.TABLE_NAME)
    public abstract void deleteAllCurrencies();

}
