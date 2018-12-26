package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.jiahaoliuliu.chutoro.entity.destination.Destination;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static android.arch.persistence.room.ForeignKey.RESTRICT;

import org.jetbrains.annotations.NotNull;

@Entity(foreignKeys = @ForeignKey(entity = PersistentDestinationGroup.class,
        parentColumns = "id",
        childColumns = "groupId",
        onUpdate = CASCADE,
        onDelete = RESTRICT),
    tableName = "Destinations",
    indices = {@Index("groupId")}) // Special index for group id since it is a foreign key
public class PersistentDestination extends Destination {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NotNull
    private long groupId;

    /**
     * The code name which appears on the sms
     */
    @NotNull
    private String codeName;

    public PersistentDestination(long groupId, String codeName, String name) {
        super(name);
        this.groupId = groupId;
        this.codeName = codeName;
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

    @NotNull
    public String getCodeName() {
        return codeName;
    }

    // This is required by Room
    public void setCodeName(@NotNull String codeName) {
        this.codeName = codeName;
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
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PersistentDestination that = (PersistentDestination) o;

        if (id != that.id) return false;
        if (groupId != that.groupId) return false;
        return codeName.equals(that.codeName);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (groupId ^ (groupId >>> 32));
        result = 31 * result + codeName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PersistentDestination{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", codeName='" + codeName + '\'' +
                ", " + super.toString() +
                '}';
    }
}
