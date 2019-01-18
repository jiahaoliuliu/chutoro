package com.jiahaoliuliu.chutoro.storagelayer;

import java.util.List;

public class PersistentCurrenciesUpdate {

    private final List<PersistentCurrency> persistentCurrencies;

    // The last time when the data was updated
    private final long lastUpdateTime;

    public PersistentCurrenciesUpdate(
            List<PersistentCurrency> persistentCurrencies, long lastUpdateTime) {
        this.persistentCurrencies = persistentCurrencies;
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<PersistentCurrency> getPersistentCurrencies() {
        return persistentCurrencies;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersistentCurrenciesUpdate that = (PersistentCurrenciesUpdate) o;

        if (lastUpdateTime != that.lastUpdateTime) return false;
        return persistentCurrencies != null ? persistentCurrencies.equals(that.persistentCurrencies) : that.persistentCurrencies == null;
    }

    @Override
    public int hashCode() {
        int result = persistentCurrencies != null ? persistentCurrencies.hashCode() : 0;
        result = 31 * result + (int) (lastUpdateTime ^ (lastUpdateTime >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PersistentCurrenciesUpdate{" +
                "persistentCurrencies=" + persistentCurrencies +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
