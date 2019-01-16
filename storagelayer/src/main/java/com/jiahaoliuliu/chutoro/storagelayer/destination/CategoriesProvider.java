package com.jiahaoliuliu.chutoro.storagelayer.destination;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class CategoriesProvider {
    private static final String JSON_FILE_NAME = "PersistentCategories.json";

    private final Context context;
    private final Gson gson;
    private PersistentCategoryUpdate persistentCategoryUpdate;

    public CategoriesProvider(Context context) {
        this.context = context;
        this.gson = new Gson();
    }

    private PersistentCategoryUpdate getPersistentCategoryUpdate() {
        if (persistentCategoryUpdate == null) {
            persistentCategoryUpdate = parseJsonFromFile();
        }

        return persistentCategoryUpdate;
    }

    public long provideNewCategoriesUpdateTime() {
        return getPersistentCategoryUpdate() == null? 0:
                persistentCategoryUpdate.getLastUpdateTime();
    }

    public List<PersistentCategory> providePersistentCategories() {
        return getPersistentCategoryUpdate() == null? new ArrayList<>():
                persistentCategoryUpdate.getPersistentCategoriesList();
    }

    private PersistentCategoryUpdate parseJsonFromFile() {
        try {
            InputStream inputStream = context.getAssets().open(JSON_FILE_NAME);
            byte[] formArray = new byte[inputStream.available()];
            inputStream.read(formArray);
            inputStream.close();
            String persistentCategoriesFileJson = new String(formArray);
            return gson.fromJson(persistentCategoriesFileJson, PersistentCategoryUpdate.class);
        } catch (IOException e) {
            Timber.e(e, "Unable to read the persistent destinations");
        }

        return null;
    }
}
