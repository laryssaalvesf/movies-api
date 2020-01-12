package br.com.movies.controller;

import br.com.movies.entities.MovieData;
import br.com.movies.model.AwardWinnersResponse;
import br.com.movies.model.MovieDataResponse;
import br.com.movies.services.MovieDataService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MovieDataService movieDataService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MovieDataResponse> getAllMovies() {
        try {
            List<MovieData> movies = getAllMovieData();
            movies.forEach(movie -> movieDataService.save(movie));
            movieDataService.findByProducers("Allan Carr");
            return ResponseEntity.ok(new MovieDataResponse(movies));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "teste", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AwardWinnersResponse> getAwardWinners() {
//        try {
//
//        } catch (IOException e) {
//            return ResponseEntity.badRequest().build();
//        }
        return null;
    }



}
