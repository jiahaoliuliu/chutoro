package com.jiahaoliuliu.chutoro.storagelayer.destination;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

// TODO: Create an abstract class for the common methods
@Dao
public abstract class DestinationGroupDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insert(PersistentDestinationGroup persistentDestinationGroup);

    public synchronized boolean insertOrUpdate(PersistentDestinationGroup persistentDestinationGroup) {
        PersistentDestinationGroup existingPersistentDestinationGroup =
                getDestinationGroupById(persistentDestinationGroup.getId());
        if (existingPersistentDestinationGroup == null) {
            return insert(persistentDestinationGroup) > 0;
        } else {
            return update(persistentDestinationGroup) > 0;
        }
    }

    @Query("Select " + PersistentDestinationGroup.TABLE_NAME + ".id as id, " +
                  PersistentDestinationGroup.TABLE_NAME + ".name as name, " +
                  PersistentCategory.TABLE_NAME + ".id as categoryId, " +
                  PersistentCategory.TABLE_NAME + ".name as category," +
                  PersistentDestinationGroup.TABLE_NAME + ".latitude as latitude, " +
                  PersistentDestinationGroup.TABLE_NAME + ".longitude as longitude, " +
                  PersistentDestinationGroup.TABLE_NAME + ".description as description " +
            "from " + PersistentDestinationGroup.TABLE_NAME +
            " left join " + PersistentCategory.TABLE_NAME +
                " on " + PersistentDestinationGroup.TABLE_NAME + ".categoryId = " +
                         PersistentCategory.TABLE_NAME + ".id " +
            "where " + PersistentDestinationGroup.TABLE_NAME + ".id == :id ")
    public abstract PersistentDestinationGroup getDestinationGroupById(long id);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    public abstract int update(PersistentDestinationGroup persistentDestinationGroup);

    @Delete
    public abstract void delete(PersistentDestinationGroup persistentDestinationGroup);

    @Query("Delete from " + PersistentDestinationGroup.TABLE_NAME)
    public abstract void deleteAllDestinationGroups();
}
