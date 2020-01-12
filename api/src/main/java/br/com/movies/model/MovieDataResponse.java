package br.com.movies.model;

import br.com.movies.entities.MovieData;

import java.util.List;

public class MovieDataResponse {

    private List<MovieData> movieData;

    public MovieDataResponse(List<MovieData> movieData) {
        this.movieData = movieData;
    }

    public List<MovieData> getMovieData() {
        return movieData;
    }

    public void setMovieData(List<MovieData> movieData) {
        this.movieData = movieData;
    }
}
