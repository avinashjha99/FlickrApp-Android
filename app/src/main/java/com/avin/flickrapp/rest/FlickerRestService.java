package com.avin.flickrapp.rest;

import com.avin.flickerapp.model.JsonFlickrFeed;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by avin on 14/06/15.
 */
public interface FlickerRestService {

    @GET("/services/feeds/photos_public.gne")
    public void getFeeds(@Query("tags") String tags, @Query("format") String format, @Query("lang") String lang, Callback<JsonFlickrFeed> callback);
}
