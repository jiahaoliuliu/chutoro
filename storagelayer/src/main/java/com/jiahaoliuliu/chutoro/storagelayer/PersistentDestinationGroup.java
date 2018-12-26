package com.jiahaoliuliu.chutoro.storagelayer;

import androidx.annotation.NonNull;

import com.jiahaoliuliu.chutoro.entity.destination.DestinationGroup;

import org.jetbrains.annotations.NotNull;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DestinationGroups")
public class PersistentDestinationGroup extends DestinationGroup {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PersistentDestinationGroup that = (PersistentDestinationGroup) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PersistentDestinationGroup{" +
                "id=" + id +
                ", " + super.toString() +
                '}';
    }
}
