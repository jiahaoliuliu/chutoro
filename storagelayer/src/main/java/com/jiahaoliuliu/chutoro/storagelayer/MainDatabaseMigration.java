package com.jiahaoliuliu.chutoro.storagelayer;

import com.jiahaoliuliu.chutoro.storagelayer.destination.PersistentCategory;
import com.jiahaoliuliu.chutoro.storagelayer.destination.PersistentDestinationGroup;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class MainDatabaseMigration {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Create a new table for persistent category
            database.execSQL("CREATE TABLE " + PersistentCategory.TABLE_NAME +
                    " ('id' INTEGER PRIMARY KEY NOT NULL," +
                      "'name' TEXT NOT NULL," +
                      "'colour' TEXT NOT NULL)");
            // Add the column CategoryId to DestinationGroups, which is a foreign key to
            // Categories(id)
            // The default value is 0 (Unknown)
            database.execSQL("ALTER TABLE " + PersistentDestinationGroup.TABLE_NAME +
                    " ADD COLUMN categoryId INTEGER NOT NULL REFERENCES " +
                    PersistentCategory.TABLE_NAME + " (id) ON DELETE NO ACTION ON UPDATE CASCADE DEFAULT 0");
            // Create the index
            database.execSQL("CREATE INDEX index_DestinationGroups_categoryId ON " +
                    PersistentDestinationGroup.TABLE_NAME + "(categoryId)");
        }
    };
}
