
package com.avin.flickerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class Media implements Parcelable {

    private String m;

    /**
     * 
     * @return
     *     The m
     */
    public String getM() {
        return m;
    }

    /**
     * 
     * @param m
     *     The m
     */
    public void setM(String m) {
        this.m = m;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.m);
    }

    public Media() {
    }

    private Media(Parcel in) {
        this.m = in.readString();
    }

    public static final Parcelable.Creator<Media> CREATOR = new Parcelable.Creator<Media>() {
        public Media createFromParcel(Parcel source) {
            return new Media(source);
        }

        public Media[] newArray(int size) {
            return new Media[size];
        }
    };
}
