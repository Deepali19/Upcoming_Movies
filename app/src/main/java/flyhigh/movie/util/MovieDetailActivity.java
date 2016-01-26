package flyhigh.movie.util;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import flyhigh.movie.R;
import flyhigh.movie.adapter.ImageAdapter;
import flyhigh.movie.app.AppController;
import flyhigh.movie.model.Movie;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends ActionBarActivity {

    private TextView texttitle;
    private static String url;
    static TextView mDotsText[];

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private int mDotsCount;
    private LinearLayout mDotsLayout;
    private RequestQueue requestQueue;
    private JSONArray ja;
    private Movie movie;
    private TextView textoverview;
    private RatingBar ratings;
    private ArrayList<Movie> movieList;
    private NetworkImageView imageview;
    private Gallery gallery;
    private ImageAdapter adapter;
    private String finalurl;
    private StringBuilder sb;
    private JSONArray Jarrayfilepath;
    private TextView overviewtext;
    private TextView movietitle;
    private float rating;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movieList = new ArrayList<Movie>();

        ratings = (RatingBar) findViewById(R.id.ratingBar);
        ratings.setEnabled(false);

        imageview = (NetworkImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();

        String title = intent.getStringExtra("TITLE");
        String movieid = intent.getStringExtra("ID");
        String overview = intent.getStringExtra("OVERVIEW");


        overviewtext = (TextView) findViewById(R.id.overview);
        movietitle = (TextView) findViewById(R.id.movietitledetail);
        movietitle.setText(title);
        overviewtext.setText(overview);
        Log.e("MOVIEID", movieid);

        sb = new StringBuilder("https://api.themoviedb.org/3/movie/");

        url = (sb.append(movieid + "/images?api_key=b7cd3340a794e5a2f35e3abb820b497f")).toString();

        Log.d("URL ", url);

        setTitle(title);

        requestQueue = Volley.newRequestQueue(this);

        Log.e("URL", sb.toString());

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            ja = response.getJSONArray("posters");
                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject obj = ja.getJSONObject(i);
                                String imageurl = obj.getString("file_path");

                                movie = new Movie();
                                movie.setRating(Float.parseFloat(obj.getString("vote_average")));
                                movie.setThumbnailUrl("https://image.tmdb.org/t/p/w500" + imageurl);
                                movieList.add(movie);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        {

                        }
                        Log.e("MOVIE OBJ", movie.toString());

                        ratings.setRating(movie.getRating());
                        imageview.setImageUrl(movie.getThumbnailUrl(), imageLoader);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                        Toast.makeText(getApplicationContext(), "Please check internet connection", Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jor);
    }
}
