package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.jiahaoliuliu.chutoro.entity.Transaction;

import java.util.Objects;

@Entity(tableName = "transactions")
public class PersistentTransaction extends Transaction {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private final int id;

    public PersistentTransaction(@NonNull int id, int quantity, String source, String destination, long date) {
        super(quantity, source, destination, date);
        this.id = id;
    }

    @NonNull
    public int getId() {
        return id;
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
        result = 31 * result + id;
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
