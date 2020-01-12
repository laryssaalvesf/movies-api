package br.com.movies.services;

import br.com.movies.entities.MovieData;
import br.com.movies.repositories.MovieDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDataService {

    @Autowired
    private MovieDataRepository movieDataRepository;

    public void save(MovieData movieData) {
        movieDataRepository.save(movieData);
    }

    public List<MovieData> findByProducers(String producers) {
        return movieDataRepository.findByProducers(producers);
    }

}
