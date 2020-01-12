package br.com.movies.utils;

import br.com.movies.entities.MovieData;
import br.com.movies.model.AwardWinner;
import br.com.movies.model.AwardWinnersResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AwardsRangeUtil {

    private static final String SPLIT_REGEX = ",|\\sand\\s";

    public static AwardWinnersResponse getAwardWinnersByRange(List<MovieData> movies) {
        Set<String> producers = new HashSet<>();
        movies.stream()
                .map(MovieData::getProducers)
                .map(str -> str.trim())
                .collect(Collectors.toSet())
                .forEach(producersNames -> {
                    producers.addAll(getIndividualProducers(producersNames));
                });

        List<AwardWinner> awardWinners = new ArrayList<>();
        producers.forEach(producer -> {
            List<MovieData> moviesByProducer = getMoviesByProducer(producer, movies);
            if(moviesByProducer.size() > 1) {
                int earliestYear = getEarliestYear(moviesByProducer);
                int lastYear = getLastYear(moviesByProducer);
                awardWinners.add(new AwardWinner(producer, lastYear - earliestYear, earliestYear, lastYear));
            }
        });

        return new AwardWinnersResponse(awardWinners.stream() .min((a1, a2) -> a1.getInterval() - a2.getInterval())
                .get(), awardWinners.stream() .max((a1, a2) -> a1.getInterval() - a2.getInterval())
                .get());
    }

    private static Set<String> getIndividualProducers(String producer) {
        Set<String> producers = new HashSet<>();
        String[] text = producer.split(SPLIT_REGEX);
        for(int i = 0; i < text.length ; i++) {
            producers.add(text[i].trim());
        }
        return producers;
    }

    private static List<MovieData> getMoviesByProducer(String producer, List<MovieData> movies) {
        return movies
                .stream()
                .filter(movieData -> movieData.getProducers().trim().toUpperCase().contains(producer.toUpperCase()))
                .collect(Collectors.toList());
    }

    private static int getEarliestYear(List<MovieData> movies) {
        return movies
                .stream()
                .map(MovieData::getYear)
                .min(Integer::compare).get();
    }

    private static int getLastYear(List<MovieData> movies) {
        return movies
                .stream()
                .map(MovieData::getYear)
                .max(Integer::compare).get();
    }

}
