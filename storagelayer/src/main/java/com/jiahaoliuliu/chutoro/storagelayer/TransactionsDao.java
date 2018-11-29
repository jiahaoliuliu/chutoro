package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public abstract class TransactionsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    protected abstract void insert(PersistentTransaction persistentTransaction);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    protected abstract void update(PersistentTransaction persistentTransaction);

    public void upsert(PersistentTransaction persistentTransaction) {
        insert(persistentTransaction);
        update(persistentTransaction);
    }

    public void upsert(List<PersistentTransaction> persistentTransactionList) {
        for (PersistentTransaction persistentTransaction :persistentTransactionList) {
            upsert(persistentTransaction);
        }
    }

    @Delete
    public abstract void delete(PersistentTransaction itemImpl);

    @Query("Delete from transactions")
    public abstract void deleteAllItems();

    @Query("Select * from transactions order by date asc")
    public abstract LiveData<List<PersistentTransaction>> getAllTransactions();
}
