package br.com.movies.controller;

import br.com.movies.model.MovieData;
import br.com.movies.model.MovieResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static br.com.movies.utils.CsvUtil.getAllMovieData;

@RestController
@RequestMapping("/pior-filme")
public class MoviesController {




    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MovieResponse> getAllMovies() {
        try {
            List<MovieData> movies = getAllMovieData();
            return ResponseEntity.ok(new MovieResponse(movies));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
