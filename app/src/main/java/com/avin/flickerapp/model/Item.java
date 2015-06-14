
package com.avin.flickerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Item implements Parcelable {

    private String title;
    private String link;
    private Media media;
    private String dateTaken;
    private String description;
    private String published;
    private String author;
    private String authorId;
    private String tags;

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The media
     */
    public Media getMedia() {
        return media;
    }

    /**
     * 
     * @param media
     *     The media
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * 
     * @return
     *     The dateTaken
     */
    public String getDateTaken() {
        return dateTaken;
    }

    /**
     * 
     * @param dateTaken
     *     The date_taken
     */
    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The published
     */
    public String getPublished() {
        return published;
    }

    /**
     * 
     * @param published
     *     The published
     */
    public void setPublished(String published) {
        this.published = published;
    }

    /**
     * 
     * @return
     *     The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The authorId
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     * 
     * @param authorId
     *     The author_id
     */
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    /**
     * 
     * @return
     *     The tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeParcelable(this.media, 0);
        dest.writeString(this.dateTaken);
        dest.writeString(this.description);
        dest.writeString(this.published);
        dest.writeString(this.author);
        dest.writeString(this.authorId);
        dest.writeString(this.tags);
    }

    public Item() {
    }

    private Item(Parcel in) {
        this.title = in.readString();
        this.link = in.readString();
        this.media = in.readParcelable(Media.class.getClassLoader());
        this.dateTaken = in.readString();
        this.description = in.readString();
        this.published = in.readString();
        this.author = in.readString();
        this.authorId = in.readString();
        this.tags = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
