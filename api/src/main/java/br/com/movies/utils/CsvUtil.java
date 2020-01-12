package br.com.movies.utils;

import br.com.movies.entities.MovieData;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
    private static final String[] COLLUMS = {"year", "title", "studios", "producers", "winner"};


    public static List<MovieData> getAllMovieData() throws IOException {
        List<MovieData> movies = new ArrayList<>();
        Reader reader = Files.newBufferedReader(Paths.get("resources/movielist.csv"));
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(MovieData.class);
        strategy.setColumnMapping(COLLUMS);

        CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                .withMappingStrategy(strategy)
                .withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(';')
                .build();

        for (MovieData movieData : (Iterable<MovieData>) csvToBean) {
            movies.add(movieData);
        }

        reader.close();

        return movies;
    }


}
