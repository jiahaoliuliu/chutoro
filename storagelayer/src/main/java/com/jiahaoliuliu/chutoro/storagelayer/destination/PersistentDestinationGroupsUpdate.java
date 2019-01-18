package com.jiahaoliuliu.chutoro.storagelayer.destination;

import java.util.List;

public class PersistentDestinationGroupsUpdate {

    private final List<PersistentDestinationGroup> persistentDestinationGroups;

    // The last time when the data was updated
    private final long lastUpdateTime;

    public PersistentDestinationGroupsUpdate(
            List<PersistentDestinationGroup> persistentDestinationGroups, long lastUpdateTime) {
        this.persistentDestinationGroups = persistentDestinationGroups;
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<PersistentDestinationGroup> getPersistentDestinationGroups() {
        return persistentDestinationGroups;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersistentDestinationGroupsUpdate that = (PersistentDestinationGroupsUpdate) o;

        if (lastUpdateTime != that.lastUpdateTime) return false;
        return persistentDestinationGroups != null ? persistentDestinationGroups.equals(that.persistentDestinationGroups) : that.persistentDestinationGroups == null;
    }

    @Override
    public int hashCode() {
        int result = persistentDestinationGroups != null ? persistentDestinationGroups.hashCode() : 0;
        result = 31 * result + (int) (lastUpdateTime ^ (lastUpdateTime >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PersistentDestinationGroupsUpdate{" +
                "persistentDestinationGroups=" + persistentDestinationGroups +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
