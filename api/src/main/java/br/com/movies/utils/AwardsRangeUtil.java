package br.com.movies.utils;

import br.com.movies.entities.MovieData;
import br.com.movies.model.AwardWinnersResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.movies.utils.CsvUtil.getAllMovieData;

public class AwardsRangeUtil {



    public static AwardWinnersResponse getAwardWinnersByRange() throws IOException {
        List<MovieData> movies = getAllMovieData()
                .stream()
                .filter(movieData -> movieData.getWinner().equalsIgnoreCase("yes"))
                .collect(Collectors.toList());

        return null;
    }

    private void defineMax(List<MovieData> movies) {

    }



}
