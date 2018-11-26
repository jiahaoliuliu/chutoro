package com.jiahaoliuliu.chutoro.storagelayer;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.jiahaoliuliu.chutoro.entity.Item;

import java.util.Date;

@Entity(tableName = "item_table")
public class ItemImpl implements Item {

    @PrimaryKey
    @NonNull
    private String id;

    private String title;

    private String description;

    private String imageUrl;

    private long timeStamp;

    public ItemImpl(String id, String title, String description, String imageUrl, long timeStamp) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.timeStamp = timeStamp;
    }

    public ItemImpl(Item anotherItem) {
        this(anotherItem.getId(), anotherItem.getTitle(), anotherItem.getDescription(),
                anotherItem.getImageUrl(), new Date().getTime());
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemImpl itemImpl = (ItemImpl) o;

        if (timeStamp != itemImpl.timeStamp) return false;
        if (!id.equals(itemImpl.id)) return false;
        if (title != null ? !title.equals(itemImpl.title) : itemImpl.title != null) return false;
        if (description != null ? !description.equals(itemImpl.description) : itemImpl.description != null)
            return false;
        return imageUrl != null ? imageUrl.equals(itemImpl.imageUrl) : itemImpl.imageUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (int) (timeStamp ^ (timeStamp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ItemImpl{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
