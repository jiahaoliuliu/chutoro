package com.jiahaoliuliu.chutoro.storagelayer;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.entity.Transaction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.jiahaoliuliu.chutoro.storagelayer.PersistentTransaction.TABLE_NAME;

/**
 * The persistent version of the transactions. This is how the transactions
 * are shown on the app
 */
@Entity(tableName = TABLE_NAME)
public class PersistentTransaction extends Transaction {

    public static final String TABLE_NAME = "Transactions";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    /**
     * Persistent transaction constructor. This should be accessible from the Storage layer because
     * there is not basic check for the parameters, so this should only be filled with the data that
     * comes from the database.
     * @param smsId     The id of the sms, if the data comes from the sms
     * @param quantity  The quantity of the transaction
     * @param currency  The currency which the transaction is made. The currency must be one of the
     *                  {@link com.jiahaoliuliu.chutoro.entity.Currency}
     * @param source    The source where the transaction comes from. It must be one of the
     *                  {@link com.jiahaoliuliu.chutoro.entity.Source}
     * @param destinationCodeName   The destination where the transaction went. This is a code name
     * @param date      The date when the transaction has happened
     */
    public PersistentTransaction(long smsId, @Nullable String originalSms, int quantity, @NonNull String currency,
                                 @NonNull String source, @NonNull String destinationCodeName, long date) {
        super(smsId, originalSms, quantity, currency, source, destinationCodeName, date);
    }

    public PersistentTransaction(ITransaction transaction) {
        super(transaction.getSmsId(), transaction.getOriginalSms(), transaction.getQuantity(),
                transaction.getCurrency(), transaction.getSource(), transaction.getDestinationCodeName(),
                transaction.getDate());
    }

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PersistentTransaction that = (PersistentTransaction) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PersistentTransaction{" +
                "id=" + id +
                super.toString() +
                '}';
    }
}
