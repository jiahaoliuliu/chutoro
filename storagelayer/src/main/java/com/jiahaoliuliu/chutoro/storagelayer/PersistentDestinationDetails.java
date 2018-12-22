package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.jiahaoliuliu.chutoro.entity.destination.DestinationDetails;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Entity
public class PersistentDestinationDetails extends DestinationDetails {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    protected PersistentDestinationDetails(@NotNull String name, @NotNull String category, @Nullable long latitude, @Nullable long longitude, @Nullable String description) {
        super(name, category, latitude, longitude, description);
    }

    @NonNull
    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PersistentDestinationDetails that = (PersistentDestinationDetails) o;

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
        return "PersistentDestinationDetails{" +
                "id=" + id +
                ", " + super.toString() +
                '}';
    }
}
