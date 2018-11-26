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
public abstract class ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    protected abstract void insert(ITransactionsImpl itemImpl);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    protected abstract void update(ITransactionsImpl itemImpl);

    public void upsert(ITransactionsImpl itemImpl) {
        insert(itemImpl);
        update(itemImpl);
    }

    public void upsert(List<ITransactionsImpl> itemsList) {
        for (ITransactionsImpl itemImpl : itemsList) {
            upsert(itemImpl);
        }
    }

    @Delete
    public abstract void delete(ITransactionsImpl itemImpl);

    @Query("Delete from ITransactionsImpl")
    public abstract void deleteAllItems();

    @Query("Select * from ITransactionsImpl order by timeStamp asc")
    public abstract Single<List<ITransactionsImpl>> getAllItems();
}
