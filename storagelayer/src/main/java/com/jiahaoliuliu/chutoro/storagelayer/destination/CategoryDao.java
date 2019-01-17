package com.jiahaoliuliu.chutoro.storagelayer.destination;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

// TODO: Create an abstract class for the common methods
@Dao
public abstract class CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insert(PersistentCategory persistentCategory);

    @Query("Select * from Categories where id == :id")
    public abstract PersistentCategory getPersistentCategoryById(long id);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    public abstract int update(PersistentCategory persistentCategory);

    @Delete
    public abstract void delete(PersistentCategory persistentCategory);

    @Query("Delete from Categories")
    public abstract void deleteAllCategories();
}
