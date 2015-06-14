package com.avin.flickrapp;

import android.app.Application;

import com.avin.flickrapp.rest.FlickerRestService;
import com.avin.flickrapp.rest.JsonpToGsonConvertor;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;

/**
 * Created by avin on 14/06/15.
 */
public class FlickrApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFlickrService();
    }

    private static RestAdapter flickerRestAdapter= null;
    private static FlickerRestService flickerRestService= null;

    private void initFlickrService(){
        flickerRestAdapter= new RestAdapter.Builder()
                .setEndpoint("https://api.flickr.com")
                .setConverter(new JsonpToGsonConvertor(new GsonBuilder().create()))
                .build();
        flickerRestService= flickerRestAdapter.create(FlickerRestService.class);
    }

    public static FlickerRestService getFlickerService(){
        return flickerRestService;
    }
}
