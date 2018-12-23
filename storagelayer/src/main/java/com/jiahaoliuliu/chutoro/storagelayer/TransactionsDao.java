package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.entity.Transaction;

import java.util.List;

@Dao
public abstract class TransactionsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insert(PersistentTransaction persistentTransaction);

    public long insert(ITransaction transaction) {
        return insert(new PersistentTransaction(transaction));
    }

    @Query("Select * from Transactions where smsId == :smsId")
    public abstract PersistentTransaction getTransactionPerSmsId(long smsId);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    public abstract void update(PersistentTransaction persistentTransaction);

    public synchronized void update(ITransaction transaction) {
        update(new PersistentTransaction(transaction));
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
    public synchronized boolean insertIfDoesNotExist(ITransaction transaction) {
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

    @Query("Delete from Transactions")
    public abstract void deleteAllItems();

    @Query("Select Transactions.quantity AS quantity, " +
            "Transactions.currency AS currency, " +
            "Transactions.source   AS source, " +
            "Transactions.destination AS destination, " +
            "Transactions.date     AS date, " +
            "Destinations.name     AS destinationName, "+
            "Destinations.latitude AS destinationLatitude," +
            "Destinations.longitude AS destinationLongitude, " +
            "Destinations.Description AS destinationDescription, " +
            "DestinationGroups.name AS destinationGroupName, " +
            "DestinationGroups.category AS destinationGroupCategory, " +
            "DestinationGroups.latitude AS destinationGroupLatitude, " +
            "DestinationGroups.longitude AS destinationGroupLongitude, " +
            "DestinationGroups.description AS destinationGroupDescription " +
            "  from Transactions, Destinations, DestinationGroups order by date desc")
    public abstract LiveData<List<TransactionShown>> getAllTransactions();

    @Query("Select * from Transactions order by date desc")
    public abstract LiveData<List<PersistentTransaction>> getAllPlainTransactions();

    // TODO: Update getAllTransactions using the follow query and returning TransactionShown
//    https://developer.android.com/training/data-storage/room/accessing-data#java
//    @Query("SELECT user.name AS userName, pet.name AS petName " +
//            "FROM user, pet " +
//            "WHERE user.id = pet.user_id")
//    public LiveData<List<UserPet>> loadUserAndPetNames();
//
//    // You can also define this class in a separate file, as long as you add the
//    // "public" access modifier.
//    static class UserPet {
//        public String userName;
//        public String petName;
//    }
}
