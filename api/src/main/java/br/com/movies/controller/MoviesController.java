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

import static br.com.movies.utils.AwardsRangeUtil.getAwardWinnersByRange;
import static br.com.movies.utils.CsvUtil.getAllMovieData;

@RestController
@RequestMapping("/consulta")
public class MoviesController {

    @Autowired
    private MovieDataService movieDataService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MovieDataResponse> getAllMovies() {
        try {
            init();
            List<MovieData> movies = movieDataService.findAll();
            return ResponseEntity.ok(new MovieDataResponse(movies));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "intervalo-premios", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AwardWinnersResponse> getAwardWinners() {
        try {
            init();
            AwardWinnersResponse awardWinnersResponse = getAwardWinnersByRange(movieDataService.findByWinner());
            return ResponseEntity.ok(awardWinnersResponse);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private void init() throws IOException {
        if(movieDataService.findAll().isEmpty()) {
            List<MovieData> movies = getAllMovieData();
            movies.forEach(movie -> movieDataService.save(movie));
        }
    }

}
