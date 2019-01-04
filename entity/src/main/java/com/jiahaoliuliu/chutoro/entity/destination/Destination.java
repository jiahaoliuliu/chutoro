package com.jiahaoliuliu.chutoro.entity.destination;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The destination where the money of the transaction went.
 * It is the branch. All the destination will have a {@link DestinationGroup}, which is the
 * Company behind that branch.
 *
 * All the branches are eligible to have its own location and destination.
 */
public class Destination {

    /**
     * Destination name. It is usually a branch name
     */
    @NotNull
    protected String name;

    /**
     * The codename for the destination. This should be unique across all the destinations
     */
    @NotNull
    protected String codeName;

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


    public Destination(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }

    // Optional constructor
    public Destination(String name, @NotNull String codeName, @Nullable long latitude, @Nullable long
            longitude, @Nullable String description) {
        this.name = name;
        this.codeName = codeName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    @NotNull
    public String getCodeName() {
        return codeName;
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
        if (!(o instanceof Destination)) return false;

        Destination that = (Destination) o;

        if (latitude != that.latitude) return false;
        if (longitude != that.longitude) return false;
        if (!name.equals(that.name)) return false;
        if (!codeName.equals(that.codeName)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + codeName.hashCode();
        result = 31 * result + (int) (latitude ^ (latitude >>> 32));
        result = 31 * result + (int) (longitude ^ (longitude >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "name='" + name + '\'' +
                ", codeName='" + codeName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", description='" + description + '\'' +
                '}';
    }
}
