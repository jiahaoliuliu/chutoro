package com.jiahaoliuliu.chutoro.entity.destination;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

import androidx.room.Ignore;

/**
 * The group which the destination belongs. If the {@link Destination} is about the branch, this class
 * describes the Company behind that branch.
 *
 * The name and the category is compulsory.
 */
public class DestinationGroup {

    @NotNull
    protected String name;

    @NotNull
    protected String category;

    @Nullable
    protected long latitude;

    @Nullable
    protected long longitude;

    @Nullable
    protected String description;

    // This field should be ignored by Room because it does not make sense to store it
    @Nullable
    @Ignore
    protected Destination[] destinations;

    /**
     * Compulsory constructor
     * @param name
     * @param category
     */
    protected DestinationGroup(@NotNull String name, @NotNull String category) {
        this.name = name;
        setCategory(category);
    }

    /**
     * Optional constructor
     */
    protected DestinationGroup(@NotNull String name, @NotNull String category, @Nullable long latitude,
                               @Nullable long longitude, @Nullable String description) {
        this.name = name;
        setCategory(category);
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    private void setCategory(String categoryString) {
        for (Category category: Category.values()) {
            if (category.toString().equalsIgnoreCase(categoryString)) {
                this.category = category.toString();
                return;
            }
        }

        throw new IllegalArgumentException("The category of the destination group cannot be " +
                "recognized");
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getCategory() {
        return category;
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

    @Nullable
    public Destination[] getDestinations() {
        return destinations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DestinationGroup that = (DestinationGroup) o;

        if (latitude != that.latitude) return false;
        if (longitude != that.longitude) return false;
        if (!name.equals(that.name)) return false;
        if (!category.equals(that.category)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(destinations, that.destinations);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + (int) (latitude ^ (latitude >>> 32));
        result = 31 * result + (int) (longitude ^ (longitude >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(destinations);
        return result;
    }

    @Override
    public String toString() {
        return "DestinationGroup{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", description='" + description + '\'' +
                ", destinations=" + Arrays.toString(destinations) +
                '}';
    }
}
