package com.jiahaoliuliu.chutoro.storagelayer.destination;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
