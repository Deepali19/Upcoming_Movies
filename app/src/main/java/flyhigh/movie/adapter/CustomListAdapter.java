package flyhigh.movie.adapter;

/**
 * Created by deeps on 1/11/2016.
 */


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import flyhigh.movie.R;
import flyhigh.movie.app.AppController;
import flyhigh.movie.model.Movie;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Movie> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView relDate = (TextView) convertView.findViewById(R.id.releaseDate);
        TextView overview = (TextView) convertView.findViewById(R.id.overview);
        TextView movieid = (TextView) convertView.findViewById(R.id.movieid);
        TextView adult = (TextView) convertView.findViewById(R.id.adult);


        // getting movie data for the row
        Movie m = movieItems.get(position);

        // set thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // set title
        title.setText(m.getTitle());

        // set release year
        relDate.setText(m.getReleaseDate());

        //set Adult
        if(String.valueOf(m.isAdult())=="true") {
            adult.setText("A");
        }else {
            adult.setText("U/A");
        }



        return convertView;
    }

}
