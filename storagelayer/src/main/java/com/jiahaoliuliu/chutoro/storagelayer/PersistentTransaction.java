package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.jiahaoliuliu.chutoro.entity.Transaction;

@Entity(tableName = "transactions")
public class PersistentTransaction extends Transaction {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    public PersistentTransaction(long smsId, int quantity, String currency,
                                 String source, String destination, long date) {
        super(smsId, quantity, currency, source, destination, date);
    }

    public PersistentTransaction(ITransaction transaction) {
        super(transaction.getSmsId(), transaction.getQuantity(), transaction.getCurrency(),
                transaction.getSource(), transaction.getDestination(), transaction.getDate());
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
