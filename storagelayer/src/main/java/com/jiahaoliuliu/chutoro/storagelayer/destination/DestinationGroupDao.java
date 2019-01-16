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

    @Query("Select * from DestinationGroups left join Category " +
            "on DestinationGroups.categoryId = Category.id where id == :id ")
    public abstract PersistentDestinationGroup getDestinationGroupById(long id);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    public abstract int update(PersistentDestinationGroup persistentDestinationGroup);

    @Delete
    public abstract void delete(PersistentDestinationGroup persistentDestinationGroup);

    @Query("Delete from DestinationGroups")
    public abstract void deleteAllDestinationGroups();
}
