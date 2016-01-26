package flyhigh.movie;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import flyhigh.movie.adapter.CustomListAdapter;
import flyhigh.movie.model.Movie;
import flyhigh.movie.util.InformationActivity;
import flyhigh.movie.util.MovieDetailActivity;

public class MovieListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    // Log tag
    private static final String TAG = MovieListActivity.class.getSimpleName();

    // Movies json url

    private static String TAG_ID="ID";
    private static String TAG_OVERVIEW="OVERVIEW";
    private static String TAG_TITLE="TITLE";



    private static final String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=b7cd3340a794e5a2f35e3abb820b497f";
    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView;
    private CustomListAdapter adapter;
    private JSONArray results = null;
    private RequestQueue requestQueue;
    private JSONArray ja;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("MovieList");

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http requeLruBitmapCacheLruBitmapCachest
        pDialog.setMessage("Loading...");
        pDialog.show();


        if(url==null)
        {
            Toast.makeText(this,"Please Check Internet settings",Toast.LENGTH_SHORT).show();
        }


        // Creating volley request obj
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                               ja = response.getJSONArray("results");

                            for(int i=0; i < ja.length(); i++){

                                JSONObject obj = ja.getJSONObject(i);
                                movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                String url=obj.getString("poster_path");
                                movie.setThumbnailUrl("https://image.tmdb.org/t/p/w500"+url);
                                String relDate=obj.getString("release_date");
                                boolean adult=obj.getBoolean("adult");
                                movie.setAdult(adult);
                                movie.setOverview(obj.getString("overview"));
                                movie.setMovieId(obj.getInt("id"));
                                movie.setReleaseDate(obj.getString("release_date"));
                                Log.e("DATE",relDate);

                                // adding movie to movies array
                                movieList.add(movie);

                            }

                        }catch(JSONException e){e.printStackTrace();}  {

                        }

                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");

                        Toast.makeText(getApplicationContext(),"...Please check Your internet connection",Toast.LENGTH_LONG).show();
                        hidePDialog();

                    }
                }
        );
        requestQueue.add(jor);
            hidePDialog();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie m = movieList.get(position);
        if(m==null) {
            Toast.makeText(getApplicationContext(),"Internet connection Error",Toast.LENGTH_LONG).show();
        }

        Intent intent =new Intent(this,MovieDetailActivity.class);
        String title=((TextView) view.findViewById(R.id.title)).getText().toString();

        String movieid =String.valueOf( m.getMovieId());

        intent.putExtra(TAG_ID,movieid);
        intent.putExtra(TAG_OVERVIEW,m.getOverview());

        intent.putExtra(TAG_TITLE,title);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Information");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getTitle().equals("Information"))
        {
            Intent intent=new Intent(this, InformationActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
