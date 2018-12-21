package com.jiahaoliuliu.chutoro.entity.destination;

/**
 * The destination where the money of the transaction went.
 * It is usually a company
 */
public class Destination {

    /**
     * This is the code name which the destination is known.
     * i.e. BlueSnap,London-GB
     */
    private String codeName;

    /**
     * The real name which the destination should be shown
     */
    private String name;

    /**
     * The category of the destination. It must be one of {@link Category}
     */
    private String category;

    private String branch;

    private long latitude;

    private long longitude;

    private String description;

    protected Destination(String codeName, String name, String category, String branch, long latitude, long longitude, String description) {
        this.codeName = codeName;
        this.name = name;
        this.category = category;
        this.branch = branch;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getBranch() {
        return branch;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

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
        if (codeName != null ? !codeName.equals(that.codeName) : that.codeName != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null)
            return false;
        if (branch != null ? !branch.equals(that.branch) : that.branch != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = codeName != null ? codeName.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (branch != null ? branch.hashCode() : 0);
        result = 31 * result + (int) (latitude ^ (latitude >>> 32));
        result = 31 * result + (int) (longitude ^ (longitude >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "codeName='" + codeName + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", branch='" + branch + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", description='" + description + '\'' +
                '}';
    }
}
