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
    public abstract long insert(PersistentTransaction persistentTransaction);

    public long insert(ITransaction transaction) {
        return insert(new PersistentTransaction(transaction));
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
            insertIfDoesNotExist(transaction);
        }
    }

    /**
     * // TODO: Extract all the logic outside of the DAO and create unit test for it
     * Inserting the transaction.
     * @param transaction The transaction to be inserted
     * @return
     *      True if the transaction has been inserted or
     *      True if there is already a transaction with the same sms id in the database
     *      False if there is any problem on the insertion
     */
    public boolean insertIfDoesNotExist(ITransaction transaction) {
        // If the transaction is not from the sms
        if (!transaction.isFromSms()) {
            PersistentTransaction persistentTransaction = new PersistentTransaction(transaction);
            return insert(persistentTransaction) > 0;
        }

        // If the transaction is from SMS
        PersistentTransaction persistentTransaction = getTransactionPerSmsId(transaction.getSmsId());
        if (persistentTransaction == null) {
            return insert(transaction) > 0;
        }

        return true;
    }

    @Delete
    public abstract void delete(PersistentTransaction itemImpl);

    @Query("Delete from transactions")
    public abstract void deleteAllItems();

    @Query("Select * from transactions order by date desc")
    public abstract LiveData<List<PersistentTransaction>> getAllTransactions();
}
