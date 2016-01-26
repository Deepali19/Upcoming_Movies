package flyhigh.movie.model;

/**
 * Created by deeps on 1/11/2016.
 */
import java.util.ArrayList;
import java.util.Date;

public class Movie {
    private String title, thumbnailUrl,overview;

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                ", movieId=" + movieId +
                ", adult=" + adult +
                ", filepath=" + filepath +
                '}';
    }

    private String releaseDate;
    private Float rating;
    private int movieId;
    private boolean adult;

    public ArrayList<String> getFilepath() {
        return filepath;
    }

    public void setFilepath(ArrayList<String> filepath) {
        this.filepath = filepath;
    }

    private ArrayList<String> filepath;


    public Movie(String title, String thumbnailUrl, String overview, String releaseDate, Float rating, int movieId, boolean adult ,ArrayList<String> filepath) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.movieId = movieId;
        this.adult = adult;
        this.filepath=filepath;
    }


    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }







}