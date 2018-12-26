package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public abstract class DestinationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insert(PersistentDestination persistentDestination);

    @Query("Select * from Destinations where codeName == :codeName")
    public abstract PersistentDestination getDestinationByCodeName(String codeName);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    public abstract void update(PersistentDestination persistentDestination);

    public synchronized boolean insertIfDoesNotExist(PersistentDestination persistentDestination) {
        PersistentDestination existingPersistentDestination = getDestinationByCodeName(persistentDestination.getCodeName());
        if (existingPersistentDestination == null) {
            return insert(persistentDestination) > 0;
        }

        return true;
    }

    @Delete
    public abstract void delete(PersistentDestination persistentDestination);
}
