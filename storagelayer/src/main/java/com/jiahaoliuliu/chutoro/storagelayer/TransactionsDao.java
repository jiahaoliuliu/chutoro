package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jiahaoliuliu.chutoro.entity.ITransaction;

import java.util.List;

@Dao
public abstract class TransactionsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert(PersistentTransaction persistentTransaction);

    public void insert(ITransaction transaction) {
        insert(new PersistentTransaction(transaction));
    }

    @Query("Select * from transactions where smsId == :smsId")
    public abstract PersistentTransaction getTransactionPerSmsId(long smsId);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    public abstract void update(PersistentTransaction persistentTransaction);

    public void update(ITransaction transaction) {
        update(new PersistentTransaction(transaction));
    }

    public void insertIfDoesNotExist(List<? extends ITransaction> transactionsList) {
        for (ITransaction transaction: transactionsList) {
            PersistentTransaction persistentTransaction = getTransactionPerSmsId(transaction.getSmsId());
            if (persistentTransaction == null) {
                insert(transaction);
            }
        }
    }

    @Delete
    public abstract void delete(PersistentTransaction itemImpl);

    @Query("Delete from transactions")
    public abstract void deleteAllItems();

    @Query("Select * from transactions order by date desc")
    public abstract LiveData<List<PersistentTransaction>> getAllTransactions();
}
