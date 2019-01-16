package com.jiahaoliuliu.chutoro.storagelayer.destination;

import org.jetbrains.annotations.NotNull;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Category")
public class PersistentCategory {

    @PrimaryKey
    @NotNull
    private long id;

    @NotNull
    private String name;

    /**
     * The colour used to paint it on the graph
     */
    @NotNull
    private String colour;

    public PersistentCategory(@NotNull long id, @NotNull String name, @NotNull String colour) {
        this.id = id;
        this.name = name;
        this.colour = colour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersistentCategory)) return false;

        PersistentCategory that = (PersistentCategory) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        return colour.equals(that.colour);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + colour.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PersistentCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
