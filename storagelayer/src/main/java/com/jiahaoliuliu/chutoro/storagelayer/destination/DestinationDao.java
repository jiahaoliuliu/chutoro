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

    @Query("Select * from " + PersistentDestination.TABLE_NAME + " where codeName == :codeName")
    public abstract PersistentDestination getDestinationByCodeName(String codeName);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    public abstract int update(PersistentDestination persistentDestination);

    public synchronized boolean insertOrUpdate(PersistentDestination persistentDestination) {
        PersistentDestination existingPersistentDestination = getDestinationByCodeName(persistentDestination.getCodeName());
        if (existingPersistentDestination == null) {
            return insert(persistentDestination) > 0;
        } else {
            return update(persistentDestination) > 0;
        }
    }

    @Delete
    public abstract void delete(PersistentDestination persistentDestination);

    @Query("Delete from " + PersistentDestination.TABLE_NAME)
    public abstract void deleteAllDestinations();
}
