package com.jiahaoliuliu.chutoro.entity.destination;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The destination where the money of the transaction went.
 * It is the branch. All the destination will have a {@link DestinationGroup}, which is the
 * Company behind that branch.
 *
 * All the branches are eligible to have its own location and destination, but most of the cases
 * it is empty.
 */

public class Destination {

    /**
     * Destination name. It is usually a branch name
     */
    @NotNull
    protected String name;

    /**
     * Independent latitude and longitude, if exist
     */
    @Nullable
    protected long latitude;

    /**
     * Independent latitude and longitude, if exist
     */
    @Nullable
    protected long longitude;

    /**
     * Independent description, if exists
     */
    @Nullable
    protected String description;

    public Destination(String name) {
        this.name = name;
    }

    // Optional constructor
    public Destination(String name, @Nullable long latitude, @Nullable long longitude, @Nullable String description) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public long getLatitude() {
        return latitude;
    }

    @Nullable
    public long getLongitude() {
        return longitude;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Destination that = (Destination) o;

        if (latitude != that.latitude) return false;
        if (longitude != that.longitude) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (latitude ^ (latitude >>> 32));
        result = 31 * result + (int) (longitude ^ (longitude >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", description='" + description + '\'' +
                '}';
    }
}
