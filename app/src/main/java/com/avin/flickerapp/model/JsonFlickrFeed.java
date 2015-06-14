
package com.avin.flickerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFlickrFeed implements Parcelable {

    private String title;
    private String link;
    private String description;
    private String modified;
    private String generator;
    private List<Item> items = new ArrayList<Item>();

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
     *     The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     * 
     * @param modified
     *     The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     * 
     * @return
     *     The generator
     */
    public String getGenerator() {
        return generator;
    }

    /**
     * 
     * @param generator
     *     The generator
     */
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.description);
        dest.writeString(this.modified);
        dest.writeString(this.generator);
        dest.writeTypedList(items);
    }

    public JsonFlickrFeed() {
    }

    private JsonFlickrFeed(Parcel in) {
        this.title = in.readString();
        this.link = in.readString();
        this.description = in.readString();
        this.modified = in.readString();
        this.generator = in.readString();
        in.readTypedList(items, Item.CREATOR);
    }

    public static final Parcelable.Creator<JsonFlickrFeed> CREATOR = new Parcelable.Creator<JsonFlickrFeed>() {
        public JsonFlickrFeed createFromParcel(Parcel source) {
            return new JsonFlickrFeed(source);
        }

        public JsonFlickrFeed[] newArray(int size) {
            return new JsonFlickrFeed[size];
        }
    };
}
