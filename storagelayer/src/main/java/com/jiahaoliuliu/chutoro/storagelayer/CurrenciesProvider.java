package com.jiahaoliuliu.chutoro.storagelayer;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

// TODO: Make this class generic
public class CurrenciesProvider {
    private static final String JSON_FILE_NAME = "PersistentCurrencies.json";

    private final Context context;
    private final Gson gson;
    private PersistentCurrenciesUpdate persistentCurrenciesUpdate;

    public CurrenciesProvider(Context context) {
        this.context = context;
        this.gson = new Gson();
    }

    private PersistentCurrenciesUpdate getPersistentCurrenciesUpdate() {
        if (persistentCurrenciesUpdate == null) {
            persistentCurrenciesUpdate = parseJsonFromFile();
        }

        return persistentCurrenciesUpdate;
    }

    public long provideNewCurrenciesUpdateTime() {
        return getPersistentCurrenciesUpdate() == null? 0:
                persistentCurrenciesUpdate.getLastUpdateTime();
    }

    public List<PersistentCurrency> providePersistentCurrencies() {
        return getPersistentCurrenciesUpdate() == null? new ArrayList<>():
                persistentCurrenciesUpdate.getPersistentCurrencies();
    }

    private PersistentCurrenciesUpdate parseJsonFromFile() {
        try {
            InputStream inputStream = context.getAssets().open(JSON_FILE_NAME);
            byte[] formArray = new byte[inputStream.available()];
            inputStream.read(formArray);
            inputStream.close();
            String persistentCurrenciesFileJson = new String(formArray);
            return gson.fromJson(persistentCurrenciesFileJson, PersistentCurrenciesUpdate.class);
        } catch (IOException e) {
            Timber.e(e, "Unable to read the persistent currencies");
        }

        return null;
    }
}
