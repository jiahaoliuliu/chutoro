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
    protected abstract void insert(ItemImpl itemImpl);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    protected abstract void update(ItemImpl itemImpl);

    public void upsert(ItemImpl itemImpl) {
        insert(itemImpl);
        update(itemImpl);
    }

    public void upsert(List<ItemImpl> itemsList) {
        for (ItemImpl itemImpl : itemsList) {
            upsert(itemImpl);
        }
    }

    @Delete
    public abstract void delete(ItemImpl itemImpl);

    @Query("Delete from item_table")
    public abstract void deleteAllItems();

    @Query("Select * from item_table order by timeStamp asc")
    public abstract Single<List<ItemImpl>> getAllItems();
}
