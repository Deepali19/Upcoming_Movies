package flyhigh.movie.adapter;

/**
 * Created by deeps on 1/21/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import flyhigh.movie.app.AppController;
import flyhigh.movie.model.Movie;


public class ImageAdapter extends BaseAdapter {

    private Activity activity;
    private List<Movie> movieItems;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    //array of integers for images IDs
    private ArrayList<String> mImageIds = new ArrayList<String>();
    private LayoutInflater inflater;

    //constructor
    public ImageAdapter (Activity activity, List<Movie> movieItems){
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {




        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView imageView = new NetworkImageView(viewGroup.getContext());
        imageLoader = AppController.getInstance().getImageLoader();

        Movie m = movieItems.get(i);



        for(int j=0; j < 5; j++) {

               mImageIds.add(m.getThumbnailUrl());

        }




        Log.e("IMAGE URL ", mImageIds.get(i));

        imageView.setImageUrl(mImageIds.get(i) ,imageLoader);


        imageView.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        return imageView;
    }
}