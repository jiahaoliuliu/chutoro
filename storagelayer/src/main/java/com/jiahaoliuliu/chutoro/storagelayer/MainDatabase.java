package com.jiahaoliuliu.chutoro.storagelayer;

import android.content.Context;
import android.os.AsyncTask;

import com.jiahaoliuliu.chutoro.entity.destination.Category;
import com.jiahaoliuliu.chutoro.storagelayer.destination.DestinationDao;
import com.jiahaoliuliu.chutoro.storagelayer.destination.DestinationGroupDao;
import com.jiahaoliuliu.chutoro.storagelayer.destination.PersistentDestination;
import com.jiahaoliuliu.chutoro.storagelayer.destination.PersistentDestinationGroup;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {
        PersistentTransaction.class,
        PersistentDestination.class,
        PersistentDestinationGroup.class}, version = 1, exportSchema = false)
public abstract class MainDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "Chutoro.sqlite";

    private static MainDatabase instance;

    public abstract TransactionsDao transactionsDao();
    public abstract DestinationDao destinationDao();
    public abstract DestinationGroupDao destinationGroupDao();

    public static synchronized MainDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                MainDatabase.class, MainDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(roomCallback)
                .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            PersistentDestinationGroup[] persistentDestinationGroups = createPersistentGroups();
            new PopulateDbAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // TODO: Update the content of the database
        }
    };

    private static PersistentDestinationGroup[] createPersistentGroups() {

        return null;
    }

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final DestinationDao destinationDao;
        private final DestinationGroupDao destinationGroupDao;

        private PopulateDbAsyncTask(MainDatabase mainDatabase) {
            this.destinationDao = mainDatabase.destinationDao();
            this.destinationGroupDao = mainDatabase.destinationGroupDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            PersistentDestinationGroup persistentDestinationGroup =
                    new PersistentDestinationGroup("TeachMeNow", Category.EDUCATION.toString());
            long teachMeNowId = destinationGroupDao.insert(persistentDestinationGroup);

            PersistentDestination persistentDestination =
                    new PersistentDestination(teachMeNowId, "BlueSnap,London-GB", "Main");

            destinationDao.insert(persistentDestination);
            return null;
        }
    }
}
