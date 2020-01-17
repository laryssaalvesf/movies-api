package br.com.movies.controller;

import br.com.movies.entities.MovieData;
import br.com.movies.model.AwardWinnersResponse;
import br.com.movies.model.MovieDataResponse;
import br.com.movies.services.MovieDataService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    private final MovieDataService movieDataService;

    @Autowired
    public MoviesController(MovieDataService movieDataService) {
        this.movieDataService = movieDataService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de todos os filmes"),
            @ApiResponse(code = 400, message = "Erro ao retornar filmes"),
    })
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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna intervalo entre prêmios (min e max) de produtores"),
            @ApiResponse(code = 400, message = "Erro ao retornar intervalos de prêmios"),
    })
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
