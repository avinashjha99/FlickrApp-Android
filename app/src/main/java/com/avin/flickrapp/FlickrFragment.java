package com.avin.flickrapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.avin.flickerapp.model.Item;
import com.avin.flickerapp.model.JsonFlickrFeed;
import com.avin.flickrapp.adapter.FlickrAdapter;
import com.avin.flickrapp.rest.FlickerRestService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FlickrFragment extends Fragment implements Callback<JsonFlickrFeed> {

    private String SAVED_STATE_FEED= "ssf";
    private String SAVED_STATE_CURRENT_VISIBLE= "sscv";

    private JsonFlickrFeed feed;
    private GridView gridView;
    private ListView listView;
    private int postion;


    public FlickrFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(null!= savedInstanceState){
            feed= savedInstanceState.getParcelable(SAVED_STATE_FEED);
            postion= savedInstanceState.getInt(SAVED_STATE_CURRENT_VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_flickr, container, false);
        listView= (ListView) v.findViewById(R.id.listView);
        gridView= (GridView) v.findViewById(R.id.gridView);
        fetchData();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listView= null;
        gridView= null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindData();
    }

    private void fetchData(){
//        FlickrApp.getFlickerAdapter()
        if(null!= feed){
            return;
        }
        Log.d("Item is null", "fetching");
        FlickerRestService service= FlickrApp.getFlickerService();
        service.getFeeds("android", "json","en-us", this);
    }

    private void bindData(){
        if(null!= feed){
            FlickrAdapter adapter= new FlickrAdapter(getActivity(), 0, feed.getItems());
            if(null!= listView){
                listView.setAdapter(adapter);
                listView.setSelection(postion);
            }
            if(null!=gridView){
                gridView.setAdapter(adapter);
                gridView.setSelection(postion);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_STATE_FEED, feed);
        int savedPosition= 0;
        if(null!= gridView){
            savedPosition= gridView.getFirstVisiblePosition();
        }
        if(null!= listView){
            savedPosition= listView.getFirstVisiblePosition();
        }
        outState.putInt(SAVED_STATE_CURRENT_VISIBLE, savedPosition);

        Log.d("-----Saving state----", "------");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("-----Restoring state---", "------");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void success(JsonFlickrFeed jsonFlickrFeed, Response response) {
        feed= jsonFlickrFeed;
        bindData();
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(getActivity(),"failed "+error.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
        new AlertDialog.Builder(getActivity())
                .setTitle("App encountered an error..")
                .setMessage(error.getLocalizedMessage())
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        fetchData();
                    }
                })
                .setNegativeButton("Exit App", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
