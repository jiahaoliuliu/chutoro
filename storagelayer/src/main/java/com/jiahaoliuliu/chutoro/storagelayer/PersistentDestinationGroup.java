package com.jiahaoliuliu.chutoro.storagelayer;

import com.jiahaoliuliu.chutoro.entity.destination.DestinationGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "DestinationGroups")
public class PersistentDestinationGroup extends DestinationGroup {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    /**
     * This field is used to link to the list of Persistent destinations when
     * the data is inserted.
     * This field shouldn't be inserted as it into the database
     * TODO: Check for TypeConverter to see if it is possible to insert the list
     * of Persistent destinations automatically
     */
    @Nullable
    @Ignore
    protected PersistentDestination[] persistentDestinations;

    protected PersistentDestinationGroup(@NotNull String name, @NotNull String category) {
        super(name, category);
    }

//    protected PersistentDestinationGroup(
//            @NotNull String name, @NotNull String category, @Nullable long latitude,
//            @Nullable long longitude, @Nullable String description) {
//        super(name, category, latitude, longitude, description);
//    }

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    // This is required by Room
    public void setName(String name) {
        this.name = name;
    }

    // This is required by Room
    public void setCategory(String category) {
        this.category = category;
    }

    // This is required by Room
    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    // This is required by Room
    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    // This is required by Room
    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public PersistentDestination[] getPersistentDestinations() {
        return persistentDestinations;
    }

    public void setPersistentDestinations(@Nullable PersistentDestination[] persistentDestinations) {
        this.persistentDestinations = persistentDestinations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PersistentDestinationGroup that = (PersistentDestinationGroup) o;

        if (id != that.id) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(persistentDestinations, that.persistentDestinations);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + Arrays.hashCode(persistentDestinations);
        return result;
    }

    @Override
    public String toString() {
        return "PersistentDestinationGroup{" +
                "id=" + id +
                ", persistentDestinations=" + Arrays.toString(persistentDestinations) +
                ", " + super.toString() +
                '}';
    }
}
