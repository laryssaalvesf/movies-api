package br.com.movies.utils;

import br.com.movies.entities.MovieData;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
    private static final String[] COLLUMS = {"year", "title", "studios", "producers", "winner"};


    public static List<MovieData> getAllMovieData() throws IOException {
        List<MovieData> movies = new ArrayList<>();
        InputStream inputStream = CsvUtil.class.getClassLoader().getResourceAsStream("movielist.csv");
        Reader reader = new InputStreamReader(inputStream);
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
