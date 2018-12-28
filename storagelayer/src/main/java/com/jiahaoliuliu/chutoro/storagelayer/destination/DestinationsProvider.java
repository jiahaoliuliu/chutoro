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
public class DestinationsProvider {

    private final Context context;
    private final Gson gson;

    public DestinationsProvider(Context context) {
        this.context = context;
        this.gson = new Gson();
    }

    public List<PersistentDestinationGroup> providePersistentDestinationGroups() {
        return parseJsonFromFile();
    }

    private List<PersistentDestinationGroup> parseJsonFromFile() {
        try {
            InputStream inputStream = context.getAssets().open("PersistentDestinations.json");
            byte[] formArray = new byte[inputStream.available()];
            inputStream.read(formArray);
            inputStream.close();
            String persistentDestinationsFileJson = new String(formArray);
            PersistentDestinationGroupsUpdate persistentDestinationGroupsUpdate =
                    gson.fromJson(persistentDestinationsFileJson, PersistentDestinationGroupsUpdate.class);
            return persistentDestinationGroupsUpdate.getPersistentDestinationGroups();
        } catch (IOException e) {
            Timber.e(e, "Unable to read the persistent destinations");
        }
        return new ArrayList<>();
    }
}
