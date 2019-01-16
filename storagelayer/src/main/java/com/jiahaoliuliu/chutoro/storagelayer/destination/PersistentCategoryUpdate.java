package com.jiahaoliuliu.chutoro.storagelayer.destination;

import java.util.List;

public class PersistentCategoryUpdate {

    private final List<PersistentCategory> persistentCategoriesList;

    // The last time when the data was updated
    private final long lastUpdateTime;

    public PersistentCategoryUpdate(List<PersistentCategory> persistentCategoriesList, long lastUpdateTime) {
        this.persistentCategoriesList = persistentCategoriesList;
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<PersistentCategory> getPersistentCategoriesList() {
        return persistentCategoriesList;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersistentCategoryUpdate)) return false;

        PersistentCategoryUpdate that = (PersistentCategoryUpdate) o;

        if (lastUpdateTime != that.lastUpdateTime) return false;
        return persistentCategoriesList != null ? persistentCategoriesList.equals(that.persistentCategoriesList) : that.persistentCategoriesList == null;
    }

    @Override
    public int hashCode() {
        int result = persistentCategoriesList != null ? persistentCategoriesList.hashCode() : 0;
        result = 31 * result + (int) (lastUpdateTime ^ (lastUpdateTime >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PersistentCategoryUpdate{" +
                "persistentCategoriesList=" + persistentCategoriesList +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
