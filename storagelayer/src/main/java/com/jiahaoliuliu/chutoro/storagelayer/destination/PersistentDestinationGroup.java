package com.jiahaoliuliu.chutoro.storagelayer.destination;

import com.jiahaoliuliu.chutoro.entity.destination.DestinationGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = PersistentCategory.class,
        parentColumns = "id",
        childColumns = "categoryId",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE),
        tableName = "DestinationGroups",
        indices = {@Index("categoryId")})
public class PersistentDestinationGroup extends DestinationGroup {

    @PrimaryKey
    @NonNull
    private long id;

    @NotNull
    private long categoryId;

    /**
     * This field is used to link to the list of Persistent destinations when
     * the data is inserted.
     * This field shouldn't be inserted as it into the database
     */
    @NotNull
    @Ignore
    protected List<PersistentDestination> persistentDestinations;

    protected PersistentDestinationGroup(@NonNull long id, @NotNull String name, @NotNull String category) {
        super(name, category);
        this.id = id;
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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
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

    @NotNull
    public List<PersistentDestination> getPersistentDestinations() {
        return persistentDestinations;
    }

    public void setPersistentDestinations(@Nullable List<PersistentDestination> persistentDestinations) {
        this.persistentDestinations = persistentDestinations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PersistentDestinationGroup that = (PersistentDestinationGroup) o;

        if (id != that.id) return false;
        return persistentDestinations != null ? persistentDestinations.equals(that.persistentDestinations) : that.persistentDestinations == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (persistentDestinations != null ? persistentDestinations.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersistentDestinationGroup{" +
                "id=" + id +
                ", persistentDestinations=" + persistentDestinations +
                ", " + super.toString() +
                '}';
    }
}
