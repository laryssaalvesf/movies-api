package br.com.movies.model;

import java.util.List;

public class MovieResponse {

    private List<MovieData> movieData;

    public MovieResponse(List<MovieData> movieData) {
        this.movieData = movieData;
    }

    public List<MovieData> getMovieData() {
        return movieData;
    }

    public void setMovieData(List<MovieData> movieData) {
        this.movieData = movieData;
    }
}
