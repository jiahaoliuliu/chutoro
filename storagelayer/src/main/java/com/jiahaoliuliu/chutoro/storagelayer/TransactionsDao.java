package com.jiahaoliuliu.chutoro.storagelayer;

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
    protected abstract void insert(Transaction transaction);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    protected abstract void update(Transaction transaction);

    public void upsert(Transaction transaction) {
        insert(transaction);
        update(transaction);
    }

    @Delete
    public abstract void delete(Transaction itemImpl);

    @Query("Delete from transactions")
    public abstract void deleteAllItems();

    @Query("Select * from transactions order by date asc")
    public abstract Single<List<Transaction>> getAllTransactions();
}
