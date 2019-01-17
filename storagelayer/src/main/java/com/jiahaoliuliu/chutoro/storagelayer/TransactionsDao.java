package com.jiahaoliuliu.chutoro.storagelayer;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.storagelayer.ui.TransactionShown;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public abstract class TransactionsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insert(PersistentTransaction persistentTransaction);

    public long insert(ITransaction transaction) {
        return insert(new PersistentTransaction(transaction));
    }

    @Query("Select * from Transactions where smsId == :smsId")
    public abstract PersistentTransaction getTransactionBySmsId(long smsId);

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
        PersistentTransaction existingPersistentTransaction = getTransactionBySmsId(transaction.getSmsId());
        if (existingPersistentTransaction == null) {
            return insert(transaction) > 0;
        }

        return true;
    }

    @Delete
    public abstract void delete(PersistentTransaction persistentTransaction);

    @Query("Delete from Transactions")
    public abstract void deleteAllTransactions();

    /**
     * Query used to (left) join the transactions table with the destinations
     * tables and destinationGroups table.
     * More data here: https://developer.android.com/training/data-storage/room/accessing-data#java
     * @return
     *      Live data about th  e left join between Transactions, Destinations and DestinationGroups
     */
    @Query("Select Transactions.quantity AS quantity, " +
            "Transactions.currency AS currency, " +
            "Transactions.source   AS source, " +
            "Transactions.destinationCodeName AS destinationCodeName, " +
            "Transactions.date     AS date, " +
            "Destinations.name     AS destinationName, "+
            "Destinations.latitude AS destinationLatitude," +
            "Destinations.longitude AS destinationLongitude, " +
            "Destinations.Description AS destinationDescription, " +
            "DestinationGroups.name AS destinationGroupName, " +
            "Categories.name AS destinationGroupCategory, " +
            "DestinationGroups.latitude AS destinationGroupLatitude, " +
            "DestinationGroups.longitude AS destinationGroupLongitude, " +
            "DestinationGroups.description AS destinationGroupDescription " +
            "  from Transactions " +
            // Left join with Destinations so if it does not exist, the destinations fields will be null
            "  left join Destinations on Transactions.destinationCodeName = Destinations.codeName " +
            // Left join with DestinationGroups so if Destinations is null, those fields will be null as well
            "  left join DestinationGroups on Destinations.groupId = DestinationGroups.id " +
            // Left join with Category so if Destinations is null, those fields will be null as well
            "  left join Categories on DestinationGroups.categoryId = Categories.id " +
            // Order
            "  order by date desc")
    public abstract LiveData<List<TransactionShown>> getAllTransactions();
}
