package com.jiahaoliuliu.chutoro.storagelayer.destination;

import com.jiahaoliuliu.chutoro.entity.destination.Destination;

import org.jetbrains.annotations.NotNull;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = PersistentDestinationGroup.class,
        parentColumns = "id",
        childColumns = "groupId",
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE),
    tableName = "Destinations",
    indices = {@Index("groupId")}) // Special index for group id since it is a foreign key
public class PersistentDestination extends Destination {

    @PrimaryKey
    @NotNull
    private long id;

    @NotNull
    private long groupId;

    public PersistentDestination(long id, long groupId, String name, String codeName) {
        super(name, codeName);
        this.id = id;
        this.groupId = groupId;
    }

//    public PersistentDestination(long groupId, String codeName, String name, @Nullable long latitude, @Nullable long longitude,
//                                 @Nullable String description) {
//        super(name, latitude, longitude, description);
//        this.groupId = groupId;
//        this.codeName = codeName;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    // This is required by Room
    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof PersistentDestination)) return false;
        if (!super.equals(o)) return false;

        PersistentDestination that = (PersistentDestination) o;

        if (id != that.id) return false;
        return groupId == that.groupId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (groupId ^ (groupId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PersistentDestination{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", " + super.toString() +
                '}';
    }
}
