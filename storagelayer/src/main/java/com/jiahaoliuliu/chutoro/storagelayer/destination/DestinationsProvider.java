package com.jiahaoliuliu.chutoro.storagelayer.destination;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Class used to provide a list of destinations and the groups
 * that it belongs
 */
// TODO: Make this class generic
public class DestinationsProvider {
    private static final String JSON_FILE_NAME = "PersistentDestinations.json";

    private final Context context;
    private final Gson gson;
    private PersistentDestinationGroupsUpdate persistentDestinationGroupsUpdate;

    public DestinationsProvider(Context context) {
        this.context = context;
        this.gson = new Gson();
    }

    private PersistentDestinationGroupsUpdate getPersistentDestinationGroupsUpdate() {
        if (persistentDestinationGroupsUpdate == null) {
            persistentDestinationGroupsUpdate = parseJsonFromFile();
        }

        return persistentDestinationGroupsUpdate;
    }

    public long provideNewDestinationsUpdateTime() {
        return getPersistentDestinationGroupsUpdate() == null? 0:
                persistentDestinationGroupsUpdate.getLastUpdateTime();
    }

    public List<PersistentDestinationGroup> providePersistentDestinationGroups() {
        return getPersistentDestinationGroupsUpdate() == null? new ArrayList<>():
                persistentDestinationGroupsUpdate.getPersistentDestinationGroups();
    }

    private PersistentDestinationGroupsUpdate parseJsonFromFile() {
        try {
            InputStream inputStream = context.getAssets().open(JSON_FILE_NAME);
            byte[] formArray = new byte[inputStream.available()];
            inputStream.read(formArray);
            inputStream.close();
            String persistentDestinationsFileJson = new String(formArray);
            return gson.fromJson(persistentDestinationsFileJson, PersistentDestinationGroupsUpdate.class);
        } catch (IOException e) {
            Timber.e(e, "Unable to read the persistent destinations");
        }

        return null;
    }
}
