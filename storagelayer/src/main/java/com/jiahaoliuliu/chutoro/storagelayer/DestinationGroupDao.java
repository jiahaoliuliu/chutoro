package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public abstract class DestinationGroupDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insert(PersistentDestinationGroup persistentDestinationGroup);

    @Query("Select * from DestinationGroups where id == :id")
    public abstract PersistentDestinationGroup getDestinationGroupById(long id);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    public abstract void update(PersistentDestinationGroup persistentDestinationGroup);

    @Delete
    public abstract void delete(PersistentDestinationGroup persistentDestinationGroup);
}
